-- phpMyAdmin SQL Dump
-- version 4.6.6
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 07-12-2017 a las 22:24:17
-- Versión del servidor: 5.7.17-log
-- Versión de PHP: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `reporte`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detallereport`
--

CREATE TABLE `detallereport` (
  `IdDetalleReport` int(11) NOT NULL,
  `idLogin` int(11) NOT NULL,
  `IdServicio` int(11) NOT NULL,
  `IdTipoReporte` int(11) NOT NULL,
  `IdDetTipoRep` int(11) NOT NULL,
  `IdProcAsis` int(11) DEFAULT NULL,
  `NomPac` varchar(100) DEFAULT NULL,
  `Documento` varchar(12) DEFAULT NULL,
  `Medicamento` varchar(30) DEFAULT NULL,
  `Lote` varchar(30) DEFAULT NULL,
  `Fabricante` varchar(30) DEFAULT NULL,
  `DescSuceso` text,
  `Dispositivo` varchar(30) DEFAULT NULL,
  `FechaSuc` date DEFAULT NULL,
  `Clasificacion` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `dettiporep`
--

CREATE TABLE `dettiporep` (
  `IdDetTipoRep` int(11) NOT NULL,
  `IdTipoReporte` int(11) NOT NULL,
  `Descripcion` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `dettiporep`
--

INSERT INTO `dettiporep` (`IdDetTipoRep`, `IdTipoReporte`, `Descripcion`) VALUES
(1, 1, 'MEDICAMENTOS'),
(2, 1, 'DISPOSITIVOS MÉDICOS/EQUIPOS BIOMEDICOS'),
(3, 1, 'TRAMITES ADMINISTRATIVOS'),
(4, 1, 'PROCESOS ASISTENCIALES'),
(5, 2, 'MEDICAMENTOS'),
(6, 2, 'DISPOSITIVOS MÉDICOS/EQUIPOS BIOMEDICOS'),
(7, 2, 'TRAMITES ADMINISTRATIVOS'),
(8, 2, 'PROCESOS ASISTENCIALES');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `login`
--

CREATE TABLE `login` (
  `idLogin` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `rol` varchar(30) NOT NULL,
  `contrasena` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `login`
--

INSERT INTO `login` (`idLogin`, `name`, `rol`, `contrasena`) VALUES
(1, 'GERENCIA', 'ADMINISTRADOR', 'gerencia123'),
(2, 'SISTEMAS', 'USUARIO-CONSULTA', 'sistemas123');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `procasis`
--

CREATE TABLE `procasis` (
  `IdProcAsis` int(11) NOT NULL,
  `Descripcion` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `procasis`
--

INSERT INTO `procasis` (`IdProcAsis`, `Descripcion`) VALUES
(1, 'ERROR EN LA IDENTIFICACIÓN DEL PACIENTE'),
(2, 'CADENA DE CUSTODIA DE PERTENENCIAS DEL USUARIO'),
(3, 'CAIDAS'),
(4, 'ULCERAS POR PRESIÓN'),
(5, 'INFECCIONES RELACIONADAS CON EL CUIDADO DE LA SALUD'),
(6, 'INFECCIÓN EN SITIO OPERATORIO'),
(7, 'CIRUGIA EN SITIO EQUIVOCADO O EN PACIENTE EQUIVOCADO'),
(8, 'ERROR EN REPORTE DE LABORATORIO CLÍNICO'),
(9, 'FALLAS EN LOS REGISTROS CLÍNICOS '),
(10, 'FUGA DEL PACIENTE');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `servicio`
--

CREATE TABLE `servicio` (
  `IdServicio` int(11) NOT NULL,
  `NomServicio` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `servicio`
--

INSERT INTO `servicio` (`IdServicio`, `NomServicio`) VALUES
(1, 'URGENCIAS'),
(2, 'HOSPITALIZACIÓN'),
(3, 'QUIROFANO'),
(4, 'LABORATORIO CLINICO'),
(5, 'RADIOLOGIA'),
(6, 'FARMACIA'),
(7, 'FACTURACIÓN'),
(8, 'ADMINISTRACION');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tiporeporte`
--

CREATE TABLE `tiporeporte` (
  `IdTipoReporte` int(11) NOT NULL,
  `NomReport` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tiporeporte`
--

INSERT INTO `tiporeporte` (`IdTipoReporte`, `NomReport`) VALUES
(1, 'EVENTO ADVERSO (OCASIONA DAÑO AL PACIENTE)'),
(2, 'INCIDENTE (PUDO OCASIONAR DAÑO AL PACIENTE)');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `detallereport`
--
ALTER TABLE `detallereport`
  ADD PRIMARY KEY (`IdDetalleReport`),
  ADD KEY `IdProcAsis` (`IdProcAsis`),
  ADD KEY `IdDetTipoRep` (`IdDetTipoRep`),
  ADD KEY `IdServicio` (`IdServicio`),
  ADD KEY `IdLogin` (`idLogin`),
  ADD KEY `IdTipoReporte` (`IdTipoReporte`);

--
-- Indices de la tabla `dettiporep`
--
ALTER TABLE `dettiporep`
  ADD PRIMARY KEY (`IdDetTipoRep`),
  ADD KEY `IdTipoReporte` (`IdTipoReporte`);

--
-- Indices de la tabla `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`idLogin`);

--
-- Indices de la tabla `procasis`
--
ALTER TABLE `procasis`
  ADD PRIMARY KEY (`IdProcAsis`);

--
-- Indices de la tabla `servicio`
--
ALTER TABLE `servicio`
  ADD PRIMARY KEY (`IdServicio`);

--
-- Indices de la tabla `tiporeporte`
--
ALTER TABLE `tiporeporte`
  ADD PRIMARY KEY (`IdTipoReporte`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `detallereport`
--
ALTER TABLE `detallereport`
  MODIFY `IdDetalleReport` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `dettiporep`
--
ALTER TABLE `dettiporep`
  MODIFY `IdDetTipoRep` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT de la tabla `login`
--
ALTER TABLE `login`
  MODIFY `idLogin` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `procasis`
--
ALTER TABLE `procasis`
  MODIFY `IdProcAsis` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT de la tabla `servicio`
--
ALTER TABLE `servicio`
  MODIFY `IdServicio` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT de la tabla `tiporeporte`
--
ALTER TABLE `tiporeporte`
  MODIFY `IdTipoReporte` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `detallereport`
--
ALTER TABLE `detallereport`
  ADD CONSTRAINT `detallereport_ibfk_1` FOREIGN KEY (`IdDetTipoRep`) REFERENCES `dettiporep` (`IdDetTipoRep`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `detallereport_ibfk_2` FOREIGN KEY (`IdServicio`) REFERENCES `servicio` (`IdServicio`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `detallereport_ibfk_3` FOREIGN KEY (`idLogin`) REFERENCES `login` (`idLogin`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `detallereport_ibfk_4` FOREIGN KEY (`IdProcAsis`) REFERENCES `procasis` (`IdProcAsis`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `detallereport_ibfk_5` FOREIGN KEY (`IdTipoReporte`) REFERENCES `tiporeporte` (`IdTipoReporte`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Filtros para la tabla `dettiporep`
--
ALTER TABLE `dettiporep`
  ADD CONSTRAINT `dettiporep_ibfk_1` FOREIGN KEY (`IdTipoReporte`) REFERENCES `tiporeporte` (`IdTipoReporte`) ON DELETE NO ACTION ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
