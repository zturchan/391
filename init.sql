--Make sure you run @setup.sql first to clear/create tables.
drop sequence test_seq;
drop sequence pic_id_sequence;

--Create sequences for auto-generating ids
create sequence test_seq start with 1 increment by 1 nomaxvalue; 
create sequence pic_id_sequence start with 1 increment by 1 nomaxvalue; 

--Create some administrators
INSERT INTO USERS values('admin','admin','a', CURRENT_DATE);
INSERT INTO PERSONS values('admin','Zak','Turchansky','325 Bulyea Road','zturchan@ualberta.ca','7804375528');
INSERT INTO USERS values('jradmin','junior','a', CURRENT_DATE);
INSERT INTO PERSONS values('jradmin','Dario','Weunsch','22212 78th Street','tlo@liquid.com','5551231234');
INSERT INTO USERS values('god','superuser','a', CURRENT_DATE);
INSERT INTO PERSONS values('god','System','Administrator','1 Infinite Loop','root@ris.ca','8885559119');

--Create some doctors
INSERT INTO USERS values('dre','beats','d', CURRENT_DATE);
INSERT INTO PERSONS values('dre','Doctor','Dre','325 CASH STREET','dre@beats.com','9119119100');
INSERT INTO USERS values('jones','aqua','d', CURRENT_DATE);
INSERT INTO PERSONS values('jones','Doctor','Jones','123 Main Street','drjones@gmail.com','5558675309');
INSERT INTO USERS values('who','???','d', CURRENT_DATE);
INSERT INTO PERSONS values('who','Time','Lord','121 Tardis Ave.','who@what.com','0000000000');
INSERT INTO USERS values('psychatog','tog','d', CURRENT_DATE);
INSERT INTO PERSONS values('psychatog','Doctor','Teeth','121 Dominia Blvd.','cards@gy.com','1212121212');

--Create some patients
INSERT INTO USERS values('english','english','p', CURRENT_DATE);
INSERT INTO PERSONS values('english','James','Harding','2 GD Street','2gd@gd.net','2222222222');
INSERT INTO USERS values('flash','ktrolster','p', CURRENT_DATE);
INSERT INTO PERSONS values('flash','Lee','Young Ho','111 gom street','flash@kr.olleh.com','1321231234');
INSERT INTO USERS values('gst','delver','p', CURRENT_DATE);
INSERT INTO PERSONS values('gst','Geist','Saint-Traft','123 mythic ave.','gst@isd.us','4422222222');
INSERT INTO USERS values('pzero','0','p', CURRENT_DATE);
INSERT INTO PERSONS values('pzero','Patient','Zero','000 diseased ave.','0@0.oo','0000044444');

--Create some radiologists
INSERT INTO USERS values('radioactiveman','rad','r', CURRENT_DATE);
INSERT INTO PERSONS values('radioactiveman','Radio-Active','Man','868 Evergreen Terrace','rad@rad.co.uk','5559995559');
INSERT INTO USERS values('nuker','zerg','r', CURRENT_DATE);
INSERT INTO PERSONS values('nuker','Sarah','Kerrigan','Koprulu Sector','sarah@dominion.com','1233214565');
INSERT INTO USERS values('tester','test','r', CURRENT_DATE);
INSERT INTO PERSONS values('tester','Test','Chambermaker','A test chamber','tester@chamber.com','4488214565');

--Create some Doctor-Patient relationships
INSERT INTO FAMILY_DOCTOR values('who','english');
INSERT INTO FAMILY_DOCTOR values('who','gst');
INSERT INTO FAMILY_DOCTOR values('dre','gst');
INSERT INTO FAMILY_DOCTOR values('psychatog','flash');


--Create some radiology records
INSERT INTO RADIOLOGY_RECORD values(test_seq.nextval, 'english', 'dre', 'radioactiveman', 'Blood tests', '01-Jan-2013', '12-Dec-2012', 'Overdose', 'Metabolites of oxycodone and hydromorphone detected');
INSERT INTO RADIOLOGY_RECORD values(test_seq.nextval, 'flash', 'psychatog', 'nuker', 'X-ray', '08-Jan-2013', '07-Jan-2013', 'Wrist injury', 'Carpal tunnel syndrome');
INSERT INTO RADIOLOGY_RECORD values(test_seq.nextval, 'gst', 'who', 'nuker', 'MRI Scan', '08-Feb-2013', '03-Feb-2013', 'Stomach abscess', 'Possibly carcinogenic');













