#!/usr/bin/env bash
set -e -u
base_curr_dir=$(dirname $0)
absolute_dir_path=$(realpath $base_curr_dir)


$absolute_dir_path/server/build.sh
$absolute_dir_path/webapp/build.sh

