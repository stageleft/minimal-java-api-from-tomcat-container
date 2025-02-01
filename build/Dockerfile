FROM tomcat:11.0.2-jdk21-temurin-noble

# Tomcat標準マニュアルをブラウザから読めるようにする設定
COPY tomcat_docs/context.xml /usr/local/tomcat/webapps.dist/docs/META-INF/
RUN ln -sf /usr/local/tomcat/webapps.dist/docs /usr/local/tomcat/webapps/docs

# サンプルアプリ
ADD build/sample-0.0.1-SNAPSHOT-plain.war /usr/local/tomcat/webapps/sample.war

EXPOSE 8080
CMD ["catalina.sh", "run"]
