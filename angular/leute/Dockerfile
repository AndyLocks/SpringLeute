FROM node

WORKDIR /app
COPY . .

RUN ls -al

RUN npm install -g @angular/cli

RUN npm install

CMD ["ng", "serve", "--host", "0.0.0.0"]
