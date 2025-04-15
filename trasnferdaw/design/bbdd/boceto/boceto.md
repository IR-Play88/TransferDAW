Jugador
CREATE TABLE Jugador (
  id_jugador INT PRIMARY KEY AUTO_INCREMENT,
  foto_url TEXT,
  nombre VARCHAR(100),
  alias VARCHAR(50) NULL,
  fecha_nacimiento DATE,
  nacionalidad VARCHAR(50),
  altura DECIMAL(4,2),
  peso DECIMAL(5,2),
  pie_dominante ENUM('Izquierdo', 'Derecho', 'Ambidiestro'),
  valor_mercado DECIMAL(12,2),
  representante_id INT, -- se mantiene
  seleccion_id INT -- se mantiene
  -- Se elimina equipo_id, posicion_id (relaciones ya están en otras tablas)
);


Equipo

CREATE TABLE Equipo (
  id_equipo INT PRIMARY KEY AUTO_INCREMENT,
  escudo_url TEXT,
  nombre VARCHAR(100),
  fecha_fundacion DATE,
  pais VARCHAR(50),
  ciudad VARCHAR(100),
  presupuesto DECIMAL(15,2),
  valor_total DECIMAL(15,2),
  entrenador_id INT,
  liga_id INT,
  FOREIGN KEY (entrenador_id) REFERENCES Entrenador(id_entrenador),
  FOREIGN KEY (liga_id) REFERENCES Competicion(id_competicion)
);

Selección 

CREATE TABLE Seleccion (
  id_seleccion INT PRIMARY KEY AUTO_INCREMENT,
  logo_url TEXT,
  nombre VARCHAR(100),
  pais VARCHAR(100),
  federacion VARCHAR(100),
  fecha_fundacion DATE,
  ranking INT,
  entrenador_id INT,
  capitan_id INT,
  FOREIGN KEY (entrenador_id) REFERENCES Entrenador(id_entrenador),
  FOREIGN KEY (capitan_id) REFERENCES Jugador(id_jugador)
);


Contrato
CREATE TABLE Contrato (
  id_contrato INT PRIMARY KEY AUTO_INCREMENT,
  jugador_id INT,
  equipo_id INT,
  fecha_inicio DATE,
  fecha_fin DATE NULL,
  salario DECIMAL(10,2),
  bonificaciones DECIMAL(10,2),
  tipo_contrato VARCHAR(50),
  clausula_rescision DECIMAL(10,2),
  activo BOOLEAN DEFAULT TRUE, -- Nuevo
  es_prestamo BOOLEAN DEFAULT FALSE -- Nuevo
);


Traspaso
CREATE TABLE Traspaso (
  id_traspaso INT PRIMARY KEY AUTO_INCREMENT,
  jugador_id INT,
  equipo_origen_id INT,
  equipo_destino_id INT,
  fecha_traspaso DATE,
  cantidad DECIMAL(10,2),
  clausula_traspaso DECIMAL(10,2),
  bonificaciones DECIMAL(10,2),
  tipo ENUM('venta', 'cesión', 'libre'), -- Nuevo
  temporada_id INT -- Nuevo
);
	

ValorMercado_Historial
CREATE TABLE ValorMercado_Historial (
  id_historial INT PRIMARY KEY AUTO_INCREMENT,
  jugador_id INT,
  fecha DATE,
  valor_mercado DECIMAL(10,2),
  motivo VARCHAR(255)
);


EstadísticasTemporada 
CREATE TABLE EstadisticasTemporada (
  id_estadistica INT PRIMARY KEY AUTO_INCREMENT,
  jugador_id INT,
  temporada_id INT,
  competicion_id INT, -- Nuevo
  goles INT,
  asistencias INT,
  partidos_jugados INT,
  minutos_jugados INT,
  tarjetas_amarillas INT,
  tarjetas_rojas INT,
  promedio_goles DECIMAL(5,2),
  promedio_asistencias DECIMAL(5,2),
  valor_mercado DECIMAL(10,2)
);


Categoria_Posición
CREATE TABLE Categoria_Posicion (
  id_categoria INT PRIMARY KEY AUTO_INCREMENT,
  nombre VARCHAR(50),
  descripcion VARCHAR(255)
);


Posicion
CREATE TABLE Posicion (
  id_posicion INT PRIMARY KEY AUTO_INCREMENT,
  nombre VARCHAR(50),
  id_categoria INT,
  descripcion VARCHAR(255),
  FOREIGN KEY (id_categoria) REFERENCES Categoria_Posicion(id_categoria)
);


Jugador_posicion
CREATE TABLE Jugador_posicion (
  jugador_id INT,
  posicion_id INT,
  temporada_id INT,
  es_principal BOOLEAN DEFAULT FALSE -- Nuevo
);


Estadio
CREATE TABLE Estadio (
  id_estadio INT PRIMARY KEY AUTO_INCREMENT,
  foto_url TEXT,
  nombre VARCHAR(255),
  capacidad INT,
  ubicacion VARCHAR(255),
  anio_inauguracion YEAR
);

CREATE TABLE Equipo_Estadio (
  equipo_id INT,
  estadio_id INT,
  fecha_inicio DATE,
  fecha_fin DATE NULL
);


Competicion
CREATE TABLE Competicion (
  id_competicion INT PRIMARY KEY AUTO_INCREMENT,
  foto_url TEXT,
  nombre VARCHAR(255),
  pais VARCHAR(100),
  numero_equipos INT,
  anio_creacion YEAR,
  tipo VARCHAR(100),
  ranking INT
);

-- Opcional si quieres guardar edición por año:
CREATE TABLE CompeticionTemporada (
  id INT PRIMARY KEY AUTO_INCREMENT,
  competicion_id INT,
  temporada_id INT,
  descripcion VARCHAR(255)
);


Representante
CREATE TABLE Representante (
  id_representante INT PRIMARY KEY AUTO_INCREMENT,
  foto_url TEXT,
  nombre VARCHAR(255),
  telefono VARCHAR(20),
  email VARCHAR(255),
  agencia VARCHAR(255),
  direccion VARCHAR(255)
);


Noticia
CREATE TABLE Noticia (
  id_noticia INT PRIMARY KEY AUTO_INCREMENT,
  foto_url TEXT,
  titulo VARCHAR(255),
  contenido TEXT,
  fecha DATE,
  jugador_id INT NULL,
  equipo_id INT NULL,
  categoria VARCHAR(50),
  FOREIGN KEY (jugador_id) REFERENCES Jugador(id_jugador),
  FOREIGN KEY (equipo_id) REFERENCES Equipo(id_equipo)
);


Entrenador
CREATE TABLE Entrenador (
  id_entrenador INT PRIMARY KEY AUTO_INCREMENT,
  foto_url TEXT,
  nombre VARCHAR(100),
  fecha_nacimiento DATE,
  nacionalidad VARCHAR(50),
  experiencia INT
);


Equipo_entrenador
CREATE TABLE Equipo_Entrenador (
  id_equipo_entrenador INT PRIMARY KEY AUTO_INCREMENT,
  entrenador_id INT,
  equipo_id INT,
  fecha_inicio DATE,
  fecha_fin DATE NULL,
  FOREIGN KEY (entrenador_id) REFERENCES Entrenador(id_entrenador),
  FOREIGN KEY (equipo_id) REFERENCES Equipo(id_equipo)
);


Temporada
CREATE TABLE Temporada (
  id_temporada INT PRIMARY KEY AUTO_INCREMENT,
  nombre VARCHAR(20),
  fecha_inicio DATE,
  fecha_fin DATE,
  descripcion VARCHAR(255)
);


Usuario
CREATE TABLE Usuario (
  id_usuario INT PRIMARY KEY AUTO_INCREMENT,
  nombre VARCHAR(100),
  email VARCHAR(150) UNIQUE,
  contraseña CHAR(60), -- Recomendado para bcrypt
  rol ENUM('normal', 'admin'),
  fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  activo BOOLEAN DEFAULT TRUE,
  ultimo_login DATETIME NULL, -- Nuevo
  email_verificado BOOLEAN DEFAULT FALSE, -- Nuevo
  token_reset_password VARCHAR(100) NULL -- Nuevo (si quieres implementar recuperación de contraseña)
);
