DELETE FROM online_shop_development.categories;

INSERT INTO online_shop_development.categories
(
  id, name, description, sort_order, createdDateTime
)

(
  SELECT ozpigeon_zcdb.categories.categories_id, ozpigeon_zcdb.categories_description.categories_name, ozpigeon_zcdb.categories_description.categories_name, ozpigeon_zcdb.categories.sort_order, NOW()
  FROM ozpigeon_zcdb.categories, ozpigeon_zcdb.categories_description
  WHERE ozpigeon_zcdb.categories.categories_id = ozpigeon_zcdb.categories_description.categories_id
);

DELETE FROM online_shop_development.category_product;

INSERT INTO online_shop_development.category_product
(
  category_id, product_id
)

(
  SELECT ozpigeon_zcdb.products_to_categories.categories_id, ozpigeon_zcdb.products_to_categories.products_id
  FROM ozpigeon_zcdb.products_to_categories
);