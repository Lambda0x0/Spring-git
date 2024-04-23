package com.example.libreria.service;
import com.example.libreria.dao.AutoreDao;
import com.example.libreria.model.Autore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AutoreServiceImpl implements AutoreService
{
  @Autowired
  private AutoreDao autoreDao;

  @Override
  public List<Autore> getAutori()
  {
    return (List<Autore>) autoreDao.findAll();
  }

  @SuppressWarnings("OptionalGetWithoutIsPresent")
  @Override
  public Autore getAutoreById(int id)
  {
    return autoreDao.findById(id).get();
  }
}