# Use an official Arch Linux base image
FROM archlinux:latest

RUN pacman -Syu --noconfirm \
    && pacman -S --noconfirm jre-openjdk git vim

# Clone the repository and set the working directory
RUN cd / && \
    git clone https://github.com/HeardAware/backend.git && \
    cd HeardAware

# Set the working directory
WORKDIR /HeardAware

# Expose port 8080
EXPOSE 8080

# Run the Java application
CMD ["java", "-jar", "herdawareserver-dist", "."]


