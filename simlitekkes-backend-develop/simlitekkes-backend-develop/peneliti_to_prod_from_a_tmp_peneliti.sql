--userweb
select '(SELECT nextval(''userweb_id_seq''))' as idx, email as username, '$2a$10$XlhmAJDYjbUyJUSRvGXH7OFdrkKchEtehFsj48kRPerPGnjR5Zluu' as pasword,
nama_lengkap as nama, email, organisasi_id as id_organisasi, true as is_active
from (
select * from (
		select *, ROW_NUMBER () OVER (
			PARTITION BY email
			ORDER BY
				nama_lengkap 
		)  from a_tmp_peneliti atp
	) as sub where sub.row_number = 1 and email is not null and email != '' and email != '-'
) as sub; 

--userweb has role
select '(SELECT nextval(''userweb_has_role_id_seq''))' as idx, CONCAT('(select id from userweb where username = ''', email , ''')') as id_userweb, '(select id from role where role_code = ''DOSEN'')' as id_role 
from (
select * from (
		select *, ROW_NUMBER () OVER (
			PARTITION BY email
			ORDER BY
				nama_lengkap 
		)  from a_tmp_peneliti2 atp
	) as sub where sub.row_number = 1 and email is not null and email != '' and email != '-'
) as sub; 

--peneliti
select CONCAT('abc(select id from userweb where username = xyz', email , 'xyz)abc') as id_user, organisasi_id as id_organisasi, gelar_depan, nama_lengkap as nama, gelar_belakang, 
nidn, program_studi , email
from (
select * from (
		select *, ROW_NUMBER () OVER (
			PARTITION BY email
			ORDER BY
				nama_lengkap 
		)  from a_tmp_peneliti2 atp
	) as sub where sub.row_number = 1 and email is not null and email != '' and email != '-'
) as sub; 