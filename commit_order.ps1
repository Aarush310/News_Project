# Run from the ROOT of your cloned group repo (VS Code terminal / PowerShell).
# Usage: powershell -ExecutionPolicy Bypass -File C:\path\to\order_commits\commit_order.ps1
$ErrorActionPreference = "Stop"
$here = Split-Path -Parent $MyInvocation.MyCommand.Path

git checkout main
git pull
git checkout -b us2-create-order

function Stage($folder, $message) {
    Copy-Item -Path (Join-Path $here "$folder\*") -Destination . -Recurse -Force
    git add -A
    git commit -m $message
}

Stage "stage1" "US2: add Order product skeleton"
Stage "stage2" "US2: add OrderTest black-box tests (all fail against skeleton)"
Stage "stage3" "US2: implement Order (all black-box tests pass)"
Stage "stage4" "US2: add Order white-box tests"
Stage "stage5" "US2: add OrderDAO and OrderCLI (Add and Find By ID, end to end with MySQL)"
Stage "stage6" "US2: add OrderTable SQL"

git push -u origin us2-create-order
Write-Host "Done. Now open a pull request on GitHub: us2-create-order -> main"
