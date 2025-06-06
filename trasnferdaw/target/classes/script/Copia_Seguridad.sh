#!/bin/bash

# Ruta donde están los archivos a copiar
ORIGEN="/home/usuario/Descargas"

# Ruta donde guardar las copias de seguridad
DESTINO="/home/usuario/Desktop/TransferDAW/trasnferdaw/src/main/resources/backups"

# Fecha y hora actuales para el nombre del backup
FECHA=$(date +"%Y_%m_%d_%H%M%S")

# Buscar y copiar todos los archivos que empiezan con 'estadisticas_' y terminan en '.txt'
for archivo in "$ORIGEN"/estadisticas_*.txt; do
    # Obtener el nombre base del archivo (sin la ruta)
    NOMBRE_ARCHIVO=$(basename "$archivo")
    
    # Crear nombre del backup con la fecha
    BACKUP_NOMBRE="${NOMBRE_ARCHIVO%.txt}_$FECHA.txt"
    
    # Copiar el archivo
    cp "$archivo" "$DESTINO/$BACKUP_NOMBRE"

    # Verificar si la copia fue exitosa
    if [ $? -eq 0 ]; then
        echo "Copia creada: $DESTINO/$BACKUP_NOMBRE"
    else
        echo "Error copiando: $archivo"
    fi
done
