#!/bin/bash

# Definimos la ruta del archivo a copiar y dónde guardarlo
RUTA_ARCHIVO="/home/usuario/Desktop/TransferDAW/trasnferdaw/src/main/resources/archivo/export.txt"
RUTA_COLOCAR="/home/usuario/Desktop/TransferDAW/trasnferdaw/src/main/resources/backups"

# Fecha actual para nombrar el backup
FECHA=$(date +"%Y_%m_%d_%H")
BACKUP="copiaoficial_$FECHA.txt"

# Creamos la copia de seguridad
cp "$RUTA_ARCHIVO" "$RUTA_COLOCAR/$BACKUP"

# Verificamos si la copia fue exitosa
if [ $? -eq 0 ]; then
    echo "Copia de seguridad creada: ${RUTA_COLOCAR}/${BACKUP}"
else
    echo "Error al realizar copia de seguridad"
    exit 1
fi
