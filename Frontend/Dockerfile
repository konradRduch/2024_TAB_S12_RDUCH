# Etap budowania
FROM node:16-alpine AS build
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

# Etap uruchomienia
FROM node:16-alpine AS run
WORKDIR /app
COPY --from=build /app ./
CMD ["node", "dist/server/entry.mjs"]