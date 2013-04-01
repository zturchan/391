LOAD DATA 
INFILE 'lob.txt'
   INTO TABLE pacs_images
   FIELDS TERMINATED BY ','   
   (record_id   CHAR(10),
   image_id    CHAR(10),
   t_filename     FILLER CHAR(100),
   r_filename     FILLER CHAR(100),
   f_filename     FILLER CHAR(100),	
   thumbnail   LOBFILE(t_filename) TERMINATED BY EOF,
   regular_size LOBFILE(r_filename) TERMINATED BY EOF,
   full_size    LOBFILE(f_filename) TERMINATED BY EOF)
