#!/usr/bin/env bash
input_file=$1

cassandra=$(docker ps | grep 'cassandra' | grep -o '[^ ]*cassandra[^ ]*'|tail -1)
spark=$(docker ps | grep 'spark:test2' | grep -o '[^ ]*spark-singleton[^ ]*')

myJar=smack-1.0-SNAPSHOT.jar
tmp_dir=tmp
data_docker=/data

function copy() {
	tmp_file=$1
	echo Copying $tmp_file file to the $spark container
	rm -rf $tmp_dir 2>/dev/null
	mkdir $tmp_dir
	tar xvf $input_file -C $tmp_dir $1
	docker cp $tmp_dir/$tmp_file $spark:$data_docker/
	rm -rf $tmp_dir 2>/dev/null
}

function reset_data_folder() {
    echo Cleaning up
	docker exec -it $spark rm -rf $data_docker
	docker exec -it $spark mkdir $data_docker
}

function submit_job(){
	echo Executing the spark job...
	class=$1
	docker exec -it \
	$spark spark-submit \
	--class $class  \
	--executor-memory 8g \
	--num-executors 1 \
    --packages datastax:spark-cassandra-connector:2.3.1-s_2.11\
	/$myJar $data_docker $cassandra
}

function submit_query(){
	echo Executing the query job...
	class=$1
	docker exec -it \
	$spark spark-submit \
	--class $class  \
	--executor-memory 8g \
	--num-executors 1 \
    --packages datastax:spark-cassandra-connector:2.3.1-s_2.11\
	/$myJar $cassandra
}

#Send jar
docker cp $myJar $spark:/

reset_data_folder
copy review.json
submit_job datahandler.ReviewDataHandler

reset_data_folder
copy photo.json
submit_job datahandler.PhotoDataHandler

reset_data_folder
copy tip.json
submit_job datahandler.TipDataHandler

reset_data_folder
copy checkin.json
submit_job datahandler.CheckInDataHandler

reset_data_folder
copy business.json
submit_job datahandler.BusinessDataHandler

reset_data_folder
copy user.json
submit_job datahandler.UserDataHandler

#submit_query query.Query