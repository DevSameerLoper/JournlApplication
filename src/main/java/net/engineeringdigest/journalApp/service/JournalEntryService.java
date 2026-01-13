package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.Users;
import net.engineeringdigest.journalApp.repositry.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class JournalEntryService {


    @Autowired
    private JournalEntryRepo journalEntryRepo;
    @Autowired
    private UserService userService;

    public void saveEntry(JournalEntry journalEntry) {

        journalEntryRepo.save(journalEntry);
    }

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName) {
        try {
            Users user = userService.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepo.save(journalEntry);
            user.getJournalEntryList().add(saved);
            userService.saveUser(user);
        } catch (Exception e) {

            System.out.println(e.getMessage());
        }

    }

    public List<JournalEntry> getAll() {
        return journalEntryRepo.findAll();
    }

    public JournalEntry getById(ObjectId id) {
        return journalEntryRepo.findById(id).orElse(null);
    }

    @Transactional
    public boolean deleteById(ObjectId id, String username) {
        boolean removed =false;
        try {
            Users byUserName = userService.findByUserName(username);
             removed = byUserName.getJournalEntryList().removeIf(x -> x.getId().equals(id));
            if (removed) {
                userService.saveUser(byUserName);
                journalEntryRepo.deleteById(id);
            }

        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("An error occured while deleting the entry." + e);
        }
        return removed;


    }
}
