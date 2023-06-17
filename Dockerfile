FROM openjdk:17.0.2-buster AS build

RUN apt-get update && \
    apt-get install -y build-essential rlwrap

WORKDIR /app

RUN curl -O https://download.clojure.org/install/linux-install-1.11.1.1347.sh && \
    chmod +x linux-install-1.11.1.1347.sh && \
    ./linux-install-1.11.1.1347.sh

COPY deps.edn .
RUN clj

COPY Makefile .
COPY index.html .
COPY src src

RUN make publish

FROM scratch

COPY --from=build /app/index.html /build_result/index.html
COPY --from=build /app/out/main.js /build_result/out/main.js

CMD [ "" ]
