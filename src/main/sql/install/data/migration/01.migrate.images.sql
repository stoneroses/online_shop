DELETE FROM online_shop_development.images;

INSERT INTO online_shop_development.images
(
  id, name, description, location, createdDateTime
)

(
  SELECT ozpigeon_zcdb.products.products_id, ozpigeon_zcdb.products.products_image, ozpigeon_zcdb.products.products_image, ozpigeon_zcdb.products.products_image, NOW()
  FROM ozpigeon_zcdb.products
);
