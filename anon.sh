#!/bin/sh

# Replace the repository.yaml with the public key
echo "Changing Private Repository Yaml to Public Yaml..."
rm repository.yaml
mv public_repo_yaml repository.yaml

# Remove anon.sh script
echo "Deleting the anon.sh script..."
rm anon.sh

# Commit the change
echo "Committing changes..."
git add .
git commit -m "Private yaml to public"

# Rewrite all commits to hide the author's name and email
echo "Rewriting all commits across all branches to remove Names and Emails of Committer and Author..."
for branch in `ls .git/refs/heads`; do
    git filter-branch --tag-name-filter cat -f --env-filter '
        export GIT_AUTHOR_NAME="REDACTED"
        export GIT_COMMITTER_NAME="REDACTED"
        export GIT_COMMITTER_EMAIL="REDACTED"
        export GIT_AUTHOR_EMAIL="REDACTED"' $branch
done

# Delete the old commits
echo "Deleting references to old commits..."
rm -rf .git/refs/original/

# Delete remotes, which might point to the old commits
echo "Deleting references to remotes..."
for r in `git remote`; do git remote rm $r; done

# Use BFG Repo Cleaner (https://rtyley.github.io/bfg-repo-cleaner/) to remove references to:
#  - anon.sh
#  - repository.yaml
#  - public_repo_yaml
# BFG Jar is assumed to be installed at /usr/local/bin
echo "Cleaning up references to anon.sh, public_repo_yaml and repository.yaml to hide history..."
java -jar /usr/local/bin/bfg.jar --delete-files "{anon.sh,public_repo_yaml,repository.yaml}" .
git reflog expire --expire=now --all && git gc --prune=now --aggressive

#Add Remote to Public repo
echo "Adding remote to public repo: off-payroll-frontend..."
git remote add public git@github.com:hmrc/off-payroll-frontend.git

echo "IMPORTANT!!!"
echo "Before pushing double check that you are NOT pushing to the Private repo. Have someone else check before you push. As you will need to force push master"
echo "Successfully finished redacted the repo."
