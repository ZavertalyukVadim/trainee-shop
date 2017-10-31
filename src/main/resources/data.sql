INSERT INTO vendor (id, name) VALUES
  (1, 'vendor')
ON CONFLICT DO NOTHING;

INSERT INTO goods (id, name,price,type,vendor_id) VALUES
  (1, 'goods',100.00,0,1),
  (2, 'goods2',1000.00,1,1)
ON CONFLICT DO NOTHING;

INSERT INTO clients (id, discount, name) VALUES
  (1, 10, 'client')
ON CONFLICT DO NOTHING;