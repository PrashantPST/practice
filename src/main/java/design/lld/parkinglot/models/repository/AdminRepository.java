package design.lld.parkinglot.models.repository;

import design.lld.parkinglot.models.account.Admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AdminRepository {
    public static Map<String, Admin> adminMap = new HashMap<>();
    public static List<Admin> admins = new ArrayList<>();

    public Admin addAdmin(Admin admin) {
        adminMap.putIfAbsent(admin.getId(), admin);
        admins.add(admin);
        return admin;
    }

    public Admin getAdminByEmail(String email) {
        Optional<Admin> admin = admins.stream().filter(adm -> adm.getEmail().equalsIgnoreCase(email)).findFirst();
        return admin.orElse(null);
    }

    public Admin getAdminById(String id) {
        return adminMap.get(id);
    }
}
