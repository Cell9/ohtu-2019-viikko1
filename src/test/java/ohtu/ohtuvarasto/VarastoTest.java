package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void VääräTilavuus() {
        varasto = new Varasto(0);
        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void kaytettyVarastoOikeaTilavuus() {
        varasto = new Varasto(1,0);

        assertEquals(1, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void kaytettyVarastoVaaraTilavuus() {
        varasto = new Varasto(0,0);

        assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void kaytettyVarastoEiSamaOikeaSaldo() {
        varasto = new Varasto(3,1);

        assertEquals(1, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void kaytettyVarastoKyllaSamaOikeaSaldo() {
        varasto = new Varasto(1,3);

        assertEquals(1, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void kaytettyVarastoVaaraSaldo() {
        varasto = new Varasto(3,-1);

        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }


    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void VirheellinenLisaaSaldoa() {
        varasto.lisaaVarastoon(-1);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void VirheellinenOttoSaldoa() {
        varasto.otaVarastosta(-1);

        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void SaldoSamaKuinTilavuus() {
        varasto.lisaaVarastoon(11);


        assertEquals(10, varasto.getTilavuus() , vertailuTarkkuus);
    }

    @Test
    public void ottaminenLiikaa() {
        varasto.otaVarastosta(11);


        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void varmistetaanStringToimivuus() {
        varasto.lisaaVarastoon(5);
        
        assertEquals("saldo = 5.0, vielä tilaa 5.0", varasto.toString());
    }


    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

}