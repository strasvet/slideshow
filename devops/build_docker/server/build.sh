#!/usr/bin/env bash
realp=$(realpath $0)
base_curr_dir=$(dirname $realp)

source $base_curr_dir/../../common-env.sh
source $base_curr_dir/../../version.properties

file_name=Dockerfile
path_file=$base_curr_dir/$file_name

base_java_build_dir=$(realpath $base_curr_dir/../../../server)

#1 step compile core
cd $base_java_build_dir/ && ./mvnw clean package -DskipTests


#2 step build docker images
cd $base_curr_dir

project_dir=${base_java_build_dir}
docker_full_name=${docker_prefix}/server:${product_version}
echo run build image name:
echo $docker_full_name
docker build -f $base_curr_dir/Dockerfile -t ${docker_full_name} ${project_dir}

#base_server_build_dir=$(realpath $base_curr_dir/../../../${project_name})
#
#docker_full_name=${docker_prefix}/${project_name}:${product_version}
#echo $docker_full_name
#
#
#
#docker build $base_server_build_dir -f $path_file -t ${docker_full_name}

