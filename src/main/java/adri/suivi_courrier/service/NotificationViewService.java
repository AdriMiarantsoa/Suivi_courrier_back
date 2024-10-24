package adri.suivi_courrier.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import adri.suivi_courrier.data.repository.NotificationViewRepository;

@Service
public class NotificationViewService {
    @Autowired
    private NotificationViewRepository  notificationViewRepository;

    public Integer countNotificationsByDept(String id_departement) {
        return notificationViewRepository.countNotificationsByDept(id_departement);
    }
}
