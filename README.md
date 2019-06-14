# Spark to Cassandra for Yelp Dataset

This project reads the yelp [dataset](https://www.yelp.com/dataset_challenge/dataset) using Spark and writes 
to Cassandra.

Both Cassandra and Spark are deployed as two different containers 
working in a Swarm. The basic pipeline of the project is described in the following steps
1. File is extracted from the dataset.
2. File is sent to the Spark container along with the projects jar. 
3. Spark job is executed which creates the necessary table for the data categories 
and writes to them 
4. In the end a query is executed which takes the data from the tables created in
Step 3 and performs join and displays the result on the console. 


## Setup 
1. Execute the `./setup.sh` file under the `run` folder. It is recommended to pause and check that both the containers 
(Spark and Cassandra) are running. 

2. Execute the `./start.sh <path_to_jar>` to submit the spark jobs for each table. 

The `./setup.sh` script will setup a swarm cluster with Spark and Cassandra 
while the `start.sh` will get the files and start the spark jobs- 
first to create tables . 

A commented command in the `start.sh` can execute a spark job to fire 
a query to retrieve the business, tip and photo data and perform a join on it and display a subset of result.
Please uncomment to execute and see the result. 

These tables can be individually checked by  firing a `SELECT` query 
using csql in the Cassandra container. 


There is already a jar in the run folder. A new jar can be made with 
`.\gradle jar` command in the base folder. 
