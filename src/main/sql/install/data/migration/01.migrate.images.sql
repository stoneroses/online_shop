DELETE FROM online_shop_development.images;

INSERT INTO online_shop_development.images
(
  id, name, description, location, createdDateTime
)

(
  SELECT ozpigeon_zcdb.products.products_id, ozpigeon_zcdb.products.products_image, ozpigeon_zcdb.products.products_image, CONCAT("/", ozpigeon_zcdb.products.products_image), NOW()
  FROM ozpigeon_zcdb.products
);

UPDATE online_shop_development.images SET online_shop_development.images.location = REPLACE(online_shop_development.images.location, ' ', '_');
UPDATE online_shop_development.images SET online_shop_development.images.location = REPLACE(online_shop_development.images.location, '(', '_');
UPDATE online_shop_development.images SET online_shop_development.images.location = REPLACE(online_shop_development.images.location, ')', '_');