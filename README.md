**BD Modelo Físico inicial:**
![Captura de pantalla 2024-05-07 102444](https://github.com/heltonsmith/mi-primer-api/assets/4924122/18712d9c-b178-4b39-beae-eebb22d841df)


Estructura de Base de datos:

--
-- Estructura de tabla para la tabla `cuenta`
--

CREATE TABLE `cuenta` (
  `usuario` varchar(45) NOT NULL,
  `clave` varchar(45) NOT NULL,
  `id_cliente` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `cuenta`
--

INSERT INTO `cuenta` (`usuario`, `clave`, `id_cliente`) VALUES
('admin', '123', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `fono`
--

CREATE TABLE `fono` (
  `id` int(11) NOT NULL,
  `numero` int(11) NOT NULL,
  `id_cliente` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `fono`
--

INSERT INTO `fono` (`id`, `numero`, `id_cliente`) VALUES
(1, 133, 1),
(2, 301, 1),
(3, 301, 1),
(4, 301, 1),
(5, 301, 1),
(6, 301, 1),
(7, 2000, 1),
(8, 2000, 1),
(9, 198, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id_cliente` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `apellido` varchar(45) NOT NULL,
  `correo` varchar(45) NOT NULL,
  `fecha_registro` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id_cliente`, `nombre`, `apellido`, `correo`, `fecha_registro`) VALUES
(1, 'juan', 'perez', 'juan@perez.com', '2024-05-06'),
(2, 'pedro', 'rojas', 'pedro@rojas.com', '2024-05-06');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cuenta`
--
ALTER TABLE `cuenta`
  ADD PRIMARY KEY (`usuario`),
  ADD KEY `id_cliente` (`id_cliente`);

--
-- Indices de la tabla `fono`
--
ALTER TABLE `fono`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_usuario` (`id_cliente`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id_cliente`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `fono`
--
ALTER TABLE `fono`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id_cliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros FK para la tabla `cuenta`
--
ALTER TABLE `cuenta`
  ADD CONSTRAINT `usuario_cuenta` FOREIGN KEY (`id_cliente`) REFERENCES `usuario` (`id_cliente`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros FK para la tabla `fono`
--
ALTER TABLE `fono`
  ADD CONSTRAINT `usuario_fono` FOREIGN KEY (`id_cliente`) REFERENCES `usuario` (`id_cliente`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;
