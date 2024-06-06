## How to run the program:
1. Run the container:
```
docker compose up -d
```
2. Check if its running:
```
docker container ls
```
3. access hdfs:
docker exec -it namenode /bin/bash

4(a). Running the WordCount program:

hadoop jar WordCount.jar WordCount sample_input demo_output_x

4(b)To check the output:
hdfs dfs -cat demo_output_x/*

5(a) Run the Character Count Program:
hadoop jar char_count.jar CharacterCount sample_input demo_output_y

5(b) To check the output:
hdfs dfs -cat demo_output_y/*


6(a) Run the Inverted Index Program:
hadoop jar inverted_index.jar InvertedIndex inverted_index_input demo_output_z

6(b) To check the output:
hdfs dfs -cat demo_output_z/*


7(a) Run the KMeans Program:
hadoop jar KMeans.jar KMeansDriver km_input demo_output_a

7(b) To check the output:
hdfs dfs -cat demo_output_a/*
inv_ind_input/doc_1_little_rays_of_moonshine.txt
inv_ind_input/doc_2_power_house.txt


 
