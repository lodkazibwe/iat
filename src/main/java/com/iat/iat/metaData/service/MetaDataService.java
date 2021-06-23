package com.iat.iat.metaData.service;

import com.iat.iat.metaData.dao.DeviceMetaDataDao;
import com.iat.iat.metaData.model.DeviceMetaData;
import com.iat.iat.user.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua_parser.Client;
import ua_parser.Parser;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.sun.org.apache.xml.internal.serializer.Method.UNKNOWN;

@Service
public class MetaDataService {
    @Autowired DeviceMetaDataDao deviceMetaDataDao;
    private final Logger logger = LoggerFactory.getLogger(MetaDataService.class);

    public void verifyDevice(User user, HttpServletRequest request) {

       String ip = extractIp(request);
        String location = extractIp(request);;

        String deviceDetails = getDeviceDetails(request.getHeader("user-agent"));

        DeviceMetaData existingDevice
                = findExistingDevice(user.getId(), deviceDetails, location);

        if (Objects.isNull(existingDevice)) {
            //unknownDeviceNotification(deviceDetails, location, ip, user.getEmail(), request.getLocale());

            DeviceMetaData deviceMetadata = new DeviceMetaData();
            deviceMetadata.setUserId(user.getId());
            deviceMetadata.setLocation(location);
            deviceMetadata.setContact(user.getContact());
            deviceMetadata.setClientIp(ip);
            deviceMetadata.setDeviceDetails(deviceDetails);
            deviceMetadata.setLastLoggedIn(new Date());
            deviceMetaDataDao.save(deviceMetadata);
        } else {
            existingDevice.setLastLoggedIn(new Date());
            deviceMetaDataDao.save(existingDevice);
        }
    }

    private DeviceMetaData findExistingDevice(
            int userId, String deviceDetails, String location) {
        List<DeviceMetaData> knownDevices
                = deviceMetaDataDao.findByUserId(userId);

        for (DeviceMetaData existingDevice : knownDevices) {
            if (existingDevice.getDeviceDetails().equals(deviceDetails)
                    && existingDevice.getLocation().equals(location)) {
                return existingDevice;
            }
        }
        return null;
    }

    public String extractIp(HttpServletRequest request) {
        String clientIp;
        String clientXForwardedForIp = request
                .getHeader("x-forwarded-for");
       /* if (nonNull(clientXForwardedForIp)) {

            clientIp = parseXForwardedHeader(clientXForwardedForIp);
        } else {*/
        clientIp = request.getRemoteAddr();
        //}
        logger.info(clientIp);
        return clientIp;
    }
    public String getDeviceDetails(String userAgent) {
        String deviceDetails = UNKNOWN;
        Parser parser = new Parser();

        Client client = parser.parse(userAgent);
        if (Objects.nonNull(client)) {
            deviceDetails = client.userAgent.family
                    + " " + client.userAgent.major + "."
                    + client.userAgent.minor + " - "
                    + client.os.family + " " + client.os.major
                    + "." + client.os.minor;
        }
        logger.info(deviceDetails);
        return deviceDetails;
    }

    public List<DeviceMetaData> getAll() {
        return deviceMetaDataDao.findAll();
    }
}
