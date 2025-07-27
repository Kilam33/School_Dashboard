-- Initialize default dashboard data
INSERT INTO dashboard_data (staff_present, staff_absent, student_present, student_absent, ms_positive, ms_negative, elem_positive, elem_negative, school_days_left, last_updated) 
VALUES (42, 3, 387, 28, 15, 3, 22, 1, 127, CURRENT_TIMESTAMP);

-- Initialize default events
INSERT INTO events (title, date, time, display_order) VALUES 
('Science Fair', 'Friday', '10:00 AM', 0),
('Parent-Teacher Conference', 'Monday', '3:00 PM', 1),
('Spring Break', 'March 15', 'All Day', 2);