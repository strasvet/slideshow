#!/bin/bash 
set -e
set -u

realp=$(realpath $0)
base_curr_dir=$(dirname $realp)

source $base_curr_dir/../../common-env.sh
source $base_curr_dir/../../version.properties

project_folder_name=webapp

base_webapp_build_dir=$(realpath $base_curr_dir/../../../$project_folder_name)

docker_full_name=${docker_prefix}/${project_folder_name}:${product_version}

### prepare & compile
temp_folder=/tmp/webcompile
if [ ! -z $temp_folder ]; then
    rm -rf $temp_folder
fi

### copy source
mkdir -p $temp_folder
cp -r $base_webapp_build_dir/* $temp_folder/

# remove old & env ide compile
if [ ! -z $temp_folder/build ]; then
   rm -rf $temp_folder/build
fi
if [ ! -z $temp_folder/node_modules ]; then
    rm -rf $temp_folder/node_modules
fi
if [ ! -z $temp_folder/package-lock.json ]; then
    rm -f $temp_folder/package-lock.json
fi

cd $temp_folder/ && npm install --legacy-peer-deps && npm run ng build --configuration=production --omit=dev && cd $base_curr_dir

# build image
echo run build image name:
echo $docker_full_name
docker build -f $base_curr_dir/Dockerfile -t ${docker_full_name} ${temp_folder}

# remove compiled
if [ ! -z $temp_folder ]; then
    rm -rf $temp_folder
fi

