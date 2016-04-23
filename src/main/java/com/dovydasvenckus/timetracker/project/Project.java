package com.dovydasvenckus.timetracker.project;

import com.dovydasvenckus.timetracker.entry.TimeEntry;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "projects")
public class Project {
    private Long id;

    private String name;

    private Date dateCreated;

    private List<TimeEntry> timeEntries = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name", unique = true, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_created", nullable = false)
    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @OneToMany(mappedBy = "project")
    public List<TimeEntry> getTimeEntries() {
        return timeEntries;
    }

    public void setTimeEntries(List<TimeEntry> timeEntries) {
        this.timeEntries = timeEntries;
    }

    public void addTimeEntry(TimeEntry timeEntry) {
        this.timeEntries.add(timeEntry);
        if (timeEntry.getProject() != this) {
            timeEntry.setProject(this);
        }
    }
}
