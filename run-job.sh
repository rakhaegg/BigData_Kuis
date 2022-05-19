FILE_JAR=target/Kuis_Hadoop-1.0-SNAPSHOT.jar
HADOOP_USERNAME=rakha
IP_NAMENODE=192.168.1.5
HOME_DIR=/home/rakha/Kuis/
NAMA_JAR_TUJUAN=Kuis_Hadoop-1.0-SNAPSHOT.jar
PACKAGE_ID=org.example.App
NAME=/home/rakha/Kuis/Kuis_Hadoop-1.0-SNAPSHOT.jar
INPUT_FOLDER=/Kuis/Input
OUTPUT_FOLDER=/Kuis/Output
clear
SCP_ARG="${HADOOP_USERNAME}@${IP_NAMENODE}:${HOME_DIR}${NAMA_JAR_TUJUAN}"
echo "Running SCP"
echo "${SCP_ARG}"
scp $FILE_JAR $SCP_ARG


echo "Connecting to NameNode and Excute MapReduce Job..."

HADOOP_JAR_COMMAND="hadoop jar ${NAME} ${PACKAGE_ID} ${INPUT_FOLDER} ${OUTPUT_FOLDER}"
LS_OUTPUT_COMMAND="hadoop fs -ls ${OUTPUT_FOLDER}"
CAT_OUTPUT_COMMAND="hadoop fs -cat ${OUTPUT_FOLDER}/part-00000"
DELETE_OUTPUT_COMMAND="hadoop fs -rm -r ${OUTPUT_FOLDER}"
ssh "${HADOOP_USERNAME}@${IP_NAMENODE}" "${HADOOP_JAR_COMMAND}; ${LS_OUTPUT_COMMAND}; ${CAT_OUTPUT_COMMAND}; ${DELETE_OUTPUT_COMMAND}; exit"
echo "Selesai"