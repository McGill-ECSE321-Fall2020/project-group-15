# to run this just do bash ./deployHeroku.sh
# Make sure that your changes are pushed to main tho
heroku config:add PROCFILE=Artsee-Backend/Procfile --app artsee-backend-2020
heroku git:remote --app artsee-backend-2020 --remote backend-heroku
git remote -v
git push backend-heroku master
