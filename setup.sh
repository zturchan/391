sqlplus -s /nolog <<EOF
connect home/passw0rd
@setup.sql
@init.sql
commit;
quit
EOF
sqlldr control=lob.ctl userid=home/passw0rd
starttomcat
