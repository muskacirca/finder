#!/bin/sh

SOURCE_PATH="/Users/muskacirca/dev/Sources"
LOCAL_REPOSITORY_PATH=$SOURCE_PATH"/repository/mvn-repository/releases/"

mvn -DskipTests -DaltDeploymentRepository=release::default::file:$LOCAL_REPOSITORY_PATH clean deploy
echo "deploy done"

echo "doing commit..."
cd $LOCAL_REPOSITORY_PATH
git add *
git commit -m "New version of project"
git push origin master

echo "done"
