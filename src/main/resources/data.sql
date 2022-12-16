insert into option (created_at,modified_at,eng_name,kor_name,price) values
    (now(),now(),'syrup','시럽',300),
    (now(),now(),'shot','샷',500);

insert into coffee (created_at, modified_at, coffee_code, eng_name, kor_name, order_status, price, size_selectable) values
    (now(), now(), 'h001', 'americano', '아메리카노', '판매중', 4500, true),
    (now(), now(), 'c001', 'ice_americano', '아이스_아메리카노', '판매중', 4500, true),
    (now(), now(), 'h002', 'caffe_latte', '카페라떼', '판매중', 5000, true),
    (now(), now(), 'c002', 'ice_caffe_latte', '아이스_카페라떼', '판매중', 5000, true),
    (now(), now(), 'h003', 'caffe_mocha', '카페모카', '판매중', 5500, true),
    (now(), now(), 'c003', 'ice_caffe_mocha', '아이스_카페모카', '판매중', 5500, true);