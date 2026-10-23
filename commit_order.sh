#!/usr/bin/env bash
# Run from the ROOT of your cloned group repo (Git Bash on Windows works).
# Usage: bash /path/to/order_commits/commit_order.sh
set -e
HERE="$(cd "$(dirname "$0")" && pwd)"

git checkout main
git pull
git checkout -b us2-create-order

stage () {   # stage <folder> <commit message>
  cp -r "$HERE/$1/." .
  git add -A
  git commit -m "$2"
}

stage stage1 "US2: add Order product skeleton"
stage stage2 "US2: add OrderTest black-box tests (all fail against skeleton)"
stage stage3 "US2: implement Order (all black-box tests pass)"
stage stage4 "US2: add Order white-box tests"
stage stage5 "US2: add OrderDAO and OrderCLI (Add and Find By ID, end to end with MySQL)"
stage stage6 "US2: add OrderTable SQL"

git push -u origin us2-create-order
echo "Done. Now open a pull request on GitHub: us2-create-order -> main"
