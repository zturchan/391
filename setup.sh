sqlplus -s /nolog <<EOF
connect zturchan/Pikachu1
@setup.sql
@init.sql
commit;
quit
EOF
sqlldr control=lob.ctl userid=zturchan/Pikachu1
starttomcat
