package coms309.RoundTrip.demo.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

/**
 * @author ranais
 */
@Entity
@DiscriminatorValue("SUSD")
public class SevenUpSevenDown extends Game
{
    @Column(name = "betAmt")
    private int betAmt;

    @Column(name = "isWinner")
    private boolean isWinner;

    @Column(name = "numGuessed")
    private int numGuessed;

    @Column(name = "numActual")
    private int numActual;

    public int getNumGuessed()
    {
        return numGuessed;
    }

    public void setNumGuessed(int numGuessed)
    {
        this.numGuessed = numGuessed;
    }

    public int getNumActual()
    {
        return numActual;
    }

    public void setNumActual(int numActual)
    {
        this.numActual = numActual;
    }

    public SevenUpSevenDown(){};

    public SevenUpSevenDown(boolean isWinner)
    {
        this.isWinner = isWinner;
    }

    public int getBetAmt()
    {
        return betAmt;
    }

    public void setBetAmt(int betAmt)
    {
        this.betAmt = betAmt;
    }

    public boolean isWinner()
    {
        return isWinner;
    }

    public void setWinner(boolean winner)
    {
        isWinner = winner;
    }
}
