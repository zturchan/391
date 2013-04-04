#Basic makefile from http://www.cs.swarthmore.edu/~newhall/unixhelp/javamakefiles.html
JC = javac
.SUFFIXES: .java .class

.java.class:
	$(JC) $*.java
	
CLASSES = \
		getinfo.java \
		getuserinfo.java \
		oneimage.java \
		picturebrowse.java 
		
default:	classes
			javac -cp ~/catalina/webapps/proj1/WEB-INF/lib/commons-fileupload-1.3.jar:/oracle/jdbc/lib/ojdbc6.jar:/usr/local/catalina/lib/servlet-api.jar:~/catalina/webapps/proj1/WEB-INF/lib/commons-io-2.4.jar UploadImage.java

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
