DELETE FROM online_shop_development.products;

INSERT INTO online_shop_development.products
(
  id, name, reference, weight, stock, price, discount, sort_order, description, createdDateTime
)

(
  SELECT ozpigeon_zcdb.products.products_id, ozpigeon_zcdb.products_description.products_name, ozpigeon_zcdb.products_description.products_name, ozpigeon_zcdb.products.products_weight, ozpigeon_zcdb.products.products_quantity, ozpigeon_zcdb.products.products_price, 0, ozpigeon_zcdb.products.products_sort_order, ozpigeon_zcdb.products_description.products_description, NOW()
  FROM ozpigeon_zcdb.products, ozpigeon_zcdb.products_description
  WHERE ozpigeon_zcdb.products.products_id = ozpigeon_zcdb.products_description.products_id
);

UPDATE online_shop_development.products SET online_shop_development.products.description = online_shop_development.products.name WHERE online_shop_development.products.description IS NULL;

DELETE FROM online_shop_development.product_image;

INSERT INTO online_shop_development.product_image
(
  product_id, image_id
)

(
  SELECT online_shop_development.products.id, online_shop_development.products.id
  FROM online_shop_development.products
);
