#!/bin/bash

# Проверяем, что переменные окружения заданы
if [[ -z "$GITHUB_REGISTRY_USERNAME" || -z "$GITHUB_REGISTRY_PASSWORD" ]]; then
  echo "Ошибка: Переменные GITHUB_REGISTRY_USERNAME или GITHUB_REGISTRY_PASSWORD не заданы."
  echo "Пожалуйста, задайте их в окружении Windows."
  exit 1
fi

# Выполняем сборку Docker-образа с передачей переменных окружения
docker build \
  --build-arg GITHUB_REGISTRY_USERNAME="$GITHUB_REGISTRY_USERNAME" \
  --build-arg GITHUB_REGISTRY_PASSWORD="$GITHUB_REGISTRY_PASSWORD" \
  -t notification-service .

# Проверяем успешность сборки
if [ $? -eq 0 ]; then
  echo "Docker-образ успешно собран."
else
  echo "Ошибка сборки Docker-образа."
  exit 1
fi
