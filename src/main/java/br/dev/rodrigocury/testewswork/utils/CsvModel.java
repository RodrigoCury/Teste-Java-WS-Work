package br.dev.rodrigocury.testewswork.utils;

import br.dev.rodrigocury.testewswork.entities.enums.FuelType;

import java.math.BigDecimal;
import java.time.Year;

public class CsvModel {
  private final Long id;
  private final Long marcaId;
  private final String marcaNome;
  private final String modelo;
  private final Short ano;
  private final FuelType combustivel;
  private final Short numPortas;
  private final BigDecimal valorFipe;
  private final String cor;

  public CsvModel(Long id, Long marcaId, String marcaNome, String modelo, Short ano, FuelType combustivel, Short numPortas, BigDecimal valorFipe, String cor) {
    this.id = id;
    this.marcaId = marcaId;
    this.marcaNome = marcaNome;
    this.modelo = modelo;
    this.ano = ano;
    this.combustivel = combustivel;
    this.numPortas = numPortas;
    this.valorFipe = valorFipe;
    this.cor = cor;
  }

  public Long getId() {
    return id;
  }

  public Long getMarcaId() {
    return marcaId;
  }

  public String getMarcaNome() {
    return marcaNome;
  }

  public String getModelo() {
    return modelo;
  }

  public Short getAno() {
    return ano;
  }

  public FuelType getCombustivel() {
    return combustivel;
  }

  public Short getNumPortas() {
    return numPortas;
  }

  public BigDecimal getValorFipe() {
    return valorFipe;
  }

  public String getCor() {
    return cor;
  }

  @Override
  public String toString() {
    return "CsvModel{" +
        "id=" + id +
        ", marcaId=" + marcaId +
        ", marcaNome='" + marcaNome + '\'' +
        ", modelo='" + modelo + '\'' +
        ", ano=" + ano +
        ", combustivel=" + combustivel +
        ", numPortas=" + numPortas +
        ", valorFipe=" + valorFipe +
        ", cor='" + cor + '\'' +
        '}';
  }
}
