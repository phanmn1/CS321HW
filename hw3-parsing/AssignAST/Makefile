.SUFFIXES:	.jj .java

NAME=Parser
GENERATED=${NAME}.java ${NAME}Constants.java ${NAME}TokenManager.java \
	  ParseException.java SimpleCharStream.java \
	  Token.java TokenMgrError.java

${NAME}: ${NAME}.java
	javac ${NAME}.java

${NAME}.java: ${NAME}.jj
	javacc ${NAME}.jj

clean:
	-rm *.class ast/*.class ${GENERATED} tests/*.out tests/*.diff
