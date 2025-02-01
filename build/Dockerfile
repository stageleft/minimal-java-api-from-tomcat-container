FROM tomcat:latest

COPY ../tomcat_docs/context.xml    /usr/local/tomcat/webapps.dist/docs/META-INF/
RUN ln -sf /usr/local/tomcat/webapps.dist/docs /usr/local/tomcat/webapps/docs

EXPOSE 8080
CMD ["catalina.sh", "run"]
