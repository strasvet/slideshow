FROM nginx:alpine


COPY ./dist/slideshow /usr/share/nginx/html

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
