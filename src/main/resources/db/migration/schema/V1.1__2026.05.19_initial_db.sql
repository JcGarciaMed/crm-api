-- 1. Tabla de Usuarios (Empleados/Asesores de venta que usan el CRM)
CREATE TABLE `user` (
                        `id`         BINARY(16)   NOT NULL,
                        `name`       VARCHAR(255) NOT NULL,
                        `email`      VARCHAR(255) NOT NULL UNIQUE,
                        `created_at` TIMESTAMP    DEFAULT CURRENT_TIMESTAMP,
                        PRIMARY KEY (`id`)
);

-- 2. Tabla de Empresas (Clientes de tipo corporativo / B2B)
CREATE TABLE `company` (
                           `id`         BINARY(16)   NOT NULL,
                           `name`       VARCHAR(255) NOT NULL,
                           `website`    VARCHAR(255),
                           `created_by` BINARY(16),
                           `created_at` TIMESTAMP    DEFAULT CURRENT_TIMESTAMP,
                           PRIMARY KEY (`id`),
                           FOREIGN KEY (`created_by`) REFERENCES `user`(`id`)
);

-- 3. Tabla de Contactos (Personas individuales, leads o empleados de una empresa)
CREATE TABLE `contact` (
                           `id`         BINARY(16)   NOT NULL,
                           `company_id` BINARY(16),  -- Relación Muchos a Uno (Varios contactos pertenecen a una empresa)
                           `first_name` VARCHAR(100) NOT NULL,
                           `last_name`  VARCHAR(100),
                           `email`      VARCHAR(255) UNIQUE,
                           `phone`      VARCHAR(50),
                           `created_by` BINARY(16),
                           `created_at` TIMESTAMP    DEFAULT CURRENT_TIMESTAMP,
                           PRIMARY KEY (`id`),
                           FOREIGN KEY (`company_id`) REFERENCES `company`(`id`),
                           FOREIGN KEY (`created_by`) REFERENCES `user`(`id`)
);

-- 4. Tabla de Etiquetas / Tags (Para clasificar contactos: "VIP", "Inversionista", "Frío", etc.)
CREATE TABLE `tag` (
                       `id`         BINARY(16)   NOT NULL,
                       `name`       VARCHAR(50)  NOT NULL UNIQUE,
                       PRIMARY KEY (`id`)
);

-- =========================================================================
-- 5. TABLA DE MUCHOS A MUCHOS (Join Table / Tabla Intermedia)
-- Un contacto puede tener muchas etiquetas, y una etiqueta puede estar en muchos contactos.
-- =========================================================================
CREATE TABLE `contact_tag` (
                               `contact_id` BINARY(16) NOT NULL,
                               `tag_id`     BINARY(16) NOT NULL,
                               PRIMARY KEY (`contact_id`, `tag_id`),
                               FOREIGN KEY (`contact_id`) REFERENCES `contact`(`id`) ON DELETE CASCADE,
                               FOREIGN KEY (`tag_id`)     REFERENCES `tag`(`id`)     ON DELETE CASCADE
);

-- 6. Tabla de Oportunidades de Venta / Negocios (Deals)
CREATE TABLE `deal` (
                        `id`         BINARY(16)     NOT NULL,
                        `contact_id` BINARY(16)     NOT NULL, -- El cliente asociado al negocio
                        `title`      VARCHAR(255)   NOT NULL, -- Ej: "Venta de Software Licencias"
                        `amount`     DECIMAL(15, 2) NOT NULL, -- Monto del negocio
                        `stage`      VARCHAR(50)    NOT NULL, -- Ej: "PROSPECCION", "PROPUESTA", "GANADO", "PERDIDO"
                        `created_by` BINARY(16),
                        `created_at` TIMESTAMP      DEFAULT CURRENT_TIMESTAMP,
                        PRIMARY KEY (`id`),
                        FOREIGN KEY (`contact_id`) REFERENCES `contact`(`id`),
                        FOREIGN KEY (`created_by`) REFERENCES `user`(`id`)
);