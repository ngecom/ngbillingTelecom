package com.ngbilling.core.server.persistence.dao.util.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.ngbilling.core.server.persistence.dao.AbstractJpaDAO;
import com.ngbilling.core.server.persistence.dao.util.CurrencyExchangeDAO;
import com.ngbilling.core.server.persistence.dto.util.CurrencyExchangeDTO;

public class CurrencyExchangeCustomDAOImpl extends AbstractJpaDAO<CurrencyExchangeDTO> implements CurrencyExchangeDAO{

	@Override
	public List<CurrencyExchangeDTO> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CurrencyExchangeDTO> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends CurrencyExchangeDTO> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends CurrencyExchangeDTO> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteInBatch(Iterable<CurrencyExchangeDTO> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CurrencyExchangeDTO getOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends CurrencyExchangeDTO> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends CurrencyExchangeDTO> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<CurrencyExchangeDTO> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends CurrencyExchangeDTO> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<CurrencyExchangeDTO> findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(CurrencyExchangeDTO entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends CurrencyExchangeDTO> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends CurrencyExchangeDTO> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends CurrencyExchangeDTO> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends CurrencyExchangeDTO> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends CurrencyExchangeDTO> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CurrencyExchangeDTO findExchange(Integer entityId, Integer currencyId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CurrencyExchangeDTO> findByEntity(Integer entityId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CurrencyExchangeDTO getExchangeRateForRange(Integer entityId, Integer currencyId, Date from, Date to) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CurrencyExchangeDTO getExchangeRateForDate(Integer entityId, Integer currencyId, Date forDate) {
		// TODO Auto-generated method stub
		return null;
	}

	
	

}
