# Build the frontend app using Node 10
FROM node:10.19 AS builder

# Setup custom arguments for building the container image
ARG BUILD_MODE=development
ARG VUE_APP_BACKEND_URL=http://localhost:8001
ARG YARN_ARGS="--non interactive --no-progress"
WORKDIR /app

# Build the app
# Based on the suggestion found at: https://vuejs.org/v2/cookbook/dockerize-vuejs-app.html
COPY package.json ./
RUN yarn install ${YARN_ARGS}
COPY . .
RUN echo "VUE_APP_BACKEND_URL=${VUE_APP_BACKEND_URL}" > .env
RUN yarn build --mode ${BUILD_MODE} ${YARN_ARGS}

# Deploy the app into a Web server container (nginx)
FROM nginx:stable-alpine
COPY --from=builder /app/dist /usr/share/nginx/html
COPY config/nginx/default.conf /etc/nginx/conf.d/default.conf
EXPOSE 80
CMD [ "nginx", "-g", "daemon off;" ]

# Set metadata information
ARG IMAGE_SOURCE="https://gitlab.pusilkom.com/ppsdm/simlitekkes-frontend"
ARG IMAGE_VERSION=""
ARG IMAGE_VENDOR="Pusat Ilmu Komputer Universitas Indonesia"
ARG IMAGE_TITLE="SIMLITEKKES Frontend"
ARG IMAGE_DESCRIPTION="Layanan frontend untuk SIMLITEKKES"
LABEL org.opencontainers.image.authors="Development Team @ Pusat Ilmu Komputer Universitas Indonesia" \
      org.opencontainers.image.source=${IMAGE_SOURCE} \
      org.opencontainers.image.version=${IMAGE_VERSION} \
      org.opencontainers.image.vendor=${IMAGE_VENDOR} \
      org.opencontainers.image.title=${IMAGE_TITLE} \
      org.opencontainers.image.description=${IMAGE_DESCRIPTION}