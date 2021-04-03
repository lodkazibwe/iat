package com.iat.iat.user.service.serviceImpl;

import com.iat.iat.exceptions.ResourceNotFoundException;
import com.iat.iat.user.converter.AdminUserConverter;
import com.iat.iat.user.dao.AdminUserDao;
import com.iat.iat.user.dto.AdminUserDto;
import com.iat.iat.user.model.AdminUser;
import com.iat.iat.user.model.Role;
import com.iat.iat.user.model.User;
import com.iat.iat.user.service.AdminService;
import com.iat.iat.wallet.dto.WalletDto;
import com.iat.iat.wallet.service.WalletService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired AdminUserConverter adminUserConverter;
    @Autowired AdminUserDao adminUserDao;
    @Autowired
    WalletService walletService;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    private final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Override
    public AdminUser addAdmin(AdminUserDto adminUserDto) {
        logger.info("checking admin...");
        boolean bool =userExists(adminUserDto.getContact());
        if(bool){
            logger.error("contact already used...");
            throw new ResourceNotFoundException("user already exists...");
        }
        logger.info("converting user...");
        AdminUser adminUser =adminUserConverter.dtoToEntity(adminUserDto);
        //logger.info("generating passcode...");
        //Random random = new Random();
        //String rand = String.format("%04d", random.nextInt(10000));
        //String pin ="W-"+1234;
       // logger.info("user passcode: "+pin);
        //adminUser.setPassword(passwordEncoder.encode(pin));
        logger.info("setting user role...");
        adminUser.setRoles(getRoles("ADMIN"));
        logger.info("sending pin to: "+adminUser.getContact());
        logger.info("saving user and creating wallet...");
        createWallet(adminUserDao.save(adminUser));
        return adminUser;

    }

    @Override
    public AdminUser addSuperUser() {
        logger.info("creating user...");
        AdminUserDto adminUserDto =new AdminUserDto();
        adminUserDto.setTittle("Super User");
        adminUserDto.setName("rootUser");
        adminUserDto.setContact("Admin");
        adminUserDto.setEmail("admin@iat.org");
        adminUserDto.setPassWord("123@abc");

        logger.info("converting user...");
        AdminUser adminUser =adminUserConverter.dtoToEntity(adminUserDto);
        logger.info("setting user role...");
        adminUser.setRoles(getRoles("ROOT"));
        logger.info("sending pin to: "+adminUser.getContact());
        logger.info("saving user and creating wallet...");
        createWallet(adminUserDao.save(adminUser));
        return adminUser;
    }

    private void createWallet(User user) {
        WalletDto walletDto =new WalletDto();
        walletDto.setUid(user.getId());
        walletDto.setBalance(0);
        walletDto.setContact(user.getContact());
        logger.info("contacting wallet service...");
        walletService.create(walletDto);
    }

    public boolean userExists(String contact){
        return adminUserDao.existsByContact(contact);
    }

    private Set<Role> getRoles(String rol){
        logger.info("Role admin...");
        Role role = new Role(rol);
        return new HashSet<>(Collections.singletonList(role));
    }

    @Override
    public void addRole(String contact, String role) {
        AdminUser adminUser =getAdmin(contact);
        Set<Role> roles=adminUser.getRoles();
        roles.add(new Role(role));
        adminUser.setRoles(roles);
        adminUserDao.save(adminUser);

    }

    @Override
    public AdminUser getAdmin(int id) {
        return adminUserDao.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("No such user With ID: " +id));

    }

    @Override
    public AdminUser getAdmin(String contact) {

        return adminUserDao.findByContact(contact);
    }

    @Override
    public List<AdminUser> getByCategory(String category) {
        return null;
    }

    @Override
    public List<AdminUser> getByTittle(String tittle) {

        return adminUserDao.findByTittle(tittle);
    }

    @Override
    public List<AdminUser> getAll() {

        return adminUserDao.findAll();
    }

    @Override
    public AdminUser updateAdmin(AdminUserDto adminUserDto) {

        logger.info("getting user by id...");
        AdminUser adminUser =getAdmin(adminUserDto.getId());
        logger.info("updating userName ...");
        adminUser.setName(adminUserDto.getName());
        return adminUserDao.save(adminUser);
    }

    @Override
    public Void DeleteAdmin(int id) {
        return null;
    }
}
