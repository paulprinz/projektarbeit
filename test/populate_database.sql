INSERT INTO user (user_name, active, birth_date, follower_count, id, country, email, password, role)
VALUES
    ('dev1',true,'2024-09-22',0,1,'Great Britain','dev1@email.com','$2a$10$EAwsItTxg6qPyErNEk.0mu6AQKLp4zh3XRuOghbhERAJ7akFVG4ta','ROLE_ADMIN'),
    ('dev2',true,'2024-09-22',1,2,'Austria','dev2@email.com','$2a$10$Ev/uL.AOuOiVSPCQCdfQkOMRwHZbuyTFvJ2TnZTSClt.KpBKyybdW','ROLE_ADMIN'),
    ('dev3',false,'2024-09-22',2,3,'Hungary','dev3@email.com','$2a$10$Xt96FEagIGGUX3lW5u3V7uNp49Gmw6F7HBaMUggyOnLqnfGGv38sm','ROLE_ADMIN'),
    ('user1',true,'1945-10-09',4,4,'Germany','user1@supermail.it','$2a$10$CLG5dj79iXvv35GjHBQ/3eY0MvqPr4jq5dLgBlqKyXIt3xKERbRCu','ROLE_USER'),
    ('user2',true,'2021-06-07',0,5,'Luxembourg','user2@gmail.com','$2a$10$.ILU.qq32dUtcme6vd.atOKmN0zdkC8bmetp8Nx4CvEWLKTbel3bW','ROLE_USER'),
    ('user3',true,'2024-09-22',100,6,'Austria','user3@email.at','$2a$10$3./j9mZl60QesCnKKzjsXOBSjuugG1SngDK3gDH/qQ35hoD9wUQA6','ROLE_USER'),
    ('user4',false,'2024-09-08',0,7,'USA','user4@gmail.com','$2a$10$M56x6pvFvUY0SJQ9OoV9qOH2XA4DnUNmReKBUErJSFb6lfGaVl3r2','ROLE_USER'),
    ('user5',false,'2024-09-22',0,8,'Austria','user5@email.com','$2a$10$6aXHSlEd1AhgDxVfBPTLIeG2gZ8oJ6xh8SIet/tLq1PzRZBc/iRE2','ROLE_USER');