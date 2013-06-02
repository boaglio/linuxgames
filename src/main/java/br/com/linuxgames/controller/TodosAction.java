package br.com.linuxgames.controller;

import java.util.ArrayList;
import java.util.Collections;

import org.apache.log4j.Logger;
import org.mentawai.core.ActionException;
import org.mentawai.core.BaseAction;
import org.mentawai.filter.AuthenticationFree;

import br.com.linuxgames.controller.cache.CacheManager;
import br.com.linuxgames.controller.cache.EmuladorCache;
import br.com.linuxgames.controller.cache.JogoCache;
import br.com.linuxgames.model.vo.EmuladorNomeComparator;
import br.com.linuxgames.model.vo.EmuladorVO;
import br.com.linuxgames.model.vo.Jogo;
import br.com.linuxgames.model.vo.JogoNomeComparator;
import br.com.linuxgames.model.vo.JogoTipoComparator;
import br.com.linuxgames.util.InfoHelper;


public class TodosAction extends BaseAction implements AuthenticationFree {

	Logger logger = Logger.getLogger(this.getClass());

	public boolean bypassAuthentication(String arg0) {
		return true;
	}

	public String execute() throws Exception
	{
		  return SUCCESS;
	}

    /**
     * action que busca um artigo
     * @return
     * @throws ActionException
     */
    public String artigos() throws ActionException {
    	InfoHelper.setExtraInfo(this);

		//Artigos
		output.setValue("artigos",CacheManager.getCacheDeArtigos().getCache());

		//Artigos externos
		output.setValue("artigosExternos",CacheManager.getCacheDeArtigosExternos().getCache());

        return SUCCESS;
    }

    /**
     * action que busca um jogo
     * @return
     * @throws ActionException
     */
    @SuppressWarnings("unchecked")
	public String jogos() throws ActionException {
    	InfoHelper.setExtraInfo(this);
    	JogoCache jogoCache = CacheManager.getCacheDeJogos();
    	JogoNomeComparator jogoNomeComparator = new JogoNomeComparator();
		output.setValue("jogos", jogoCache.getCache());
		ArrayList<Jogo> jogos =  new ArrayList<Jogo>(jogoCache.getCache());
		Collections.sort(jogos,jogoNomeComparator);
		output.setValue("jogosOrderByNome", jogos);
		Collections.sort(jogos,new JogoTipoComparator());
		output.setValue("jogosOrderByTipo", jogos);

	    ArrayList<Jogo> jogosComA = new ArrayList<Jogo>();
	    ArrayList<Jogo> jogosComB = new ArrayList<Jogo>();
	    ArrayList<Jogo> jogosComC = new ArrayList<Jogo>();
	    ArrayList<Jogo> jogosComD = new ArrayList<Jogo>();
	    ArrayList<Jogo> jogosComE = new ArrayList<Jogo>();
	    ArrayList<Jogo> jogosComF = new ArrayList<Jogo>();
	    ArrayList<Jogo> jogosComG = new ArrayList<Jogo>();
	    ArrayList<Jogo> jogosComH = new ArrayList<Jogo>();
	    ArrayList<Jogo> jogosComI = new ArrayList<Jogo>();
	    ArrayList<Jogo> jogosComJ = new ArrayList<Jogo>();
	    ArrayList<Jogo> jogosComK = new ArrayList<Jogo>();
	    ArrayList<Jogo> jogosComL = new ArrayList<Jogo>();
	    ArrayList<Jogo> jogosComM = new ArrayList<Jogo>();
	    ArrayList<Jogo> jogosComN = new ArrayList<Jogo>();
	    ArrayList<Jogo> jogosComO = new ArrayList<Jogo>();
	    ArrayList<Jogo> jogosComP = new ArrayList<Jogo>();
	    ArrayList<Jogo> jogosComQ = new ArrayList<Jogo>();
	    ArrayList<Jogo> jogosComR = new ArrayList<Jogo>();
	    ArrayList<Jogo> jogosComS = new ArrayList<Jogo>();
	    ArrayList<Jogo> jogosComT = new ArrayList<Jogo>();
	    ArrayList<Jogo> jogosComU = new ArrayList<Jogo>();
	    ArrayList<Jogo> jogosComV = new ArrayList<Jogo>();
	    ArrayList<Jogo> jogosComW = new ArrayList<Jogo>();
	    ArrayList<Jogo> jogosComX = new ArrayList<Jogo>();
	    ArrayList<Jogo> jogosComY = new ArrayList<Jogo>();
	    ArrayList<Jogo> jogosComZ = new ArrayList<Jogo>();

	    ArrayList<Jogo> tipoAcao = new ArrayList<Jogo>();
	    ArrayList<Jogo> tipoAdventure = new ArrayList<Jogo>();
	    ArrayList<Jogo> tipoArcade = new ArrayList<Jogo>();
	    ArrayList<Jogo> tipoEsporte = new ArrayList<Jogo>();
	    ArrayList<Jogo> tipoEstrategia = new ArrayList<Jogo>();
	    ArrayList<Jogo> tipoFPS = new ArrayList<Jogo>();
	    ArrayList<Jogo> tipoInfantil = new ArrayList<Jogo>();
	    ArrayList<Jogo> tipoLuta = new ArrayList<Jogo>();
	    ArrayList<Jogo> tipoMMORPG = new ArrayList<Jogo>();
	    ArrayList<Jogo> tipoQuebraCabeca = new ArrayList<Jogo>();
	    ArrayList<Jogo> tipoRPG = new ArrayList<Jogo>();
	    ArrayList<Jogo> tipoSimulacao = new ArrayList<Jogo>();

	    for (Jogo element : jogos) {
		  String nomeDoJogo = element.getNome().toUpperCase();
		  if (nomeDoJogo.charAt(0)=='A') jogosComA.add(element);
		  if (nomeDoJogo.charAt(0)=='B') jogosComB.add(element);
		  if (nomeDoJogo.charAt(0)=='C') jogosComC.add(element);
		  if (nomeDoJogo.charAt(0)=='D') jogosComD.add(element);
		  if (nomeDoJogo.charAt(0)=='E') jogosComE.add(element);
		  if (nomeDoJogo.charAt(0)=='F') jogosComF.add(element);
		  if (nomeDoJogo.charAt(0)=='G') jogosComG.add(element);
		  if (nomeDoJogo.charAt(0)=='H') jogosComH.add(element);
		  if (nomeDoJogo.charAt(0)=='I') jogosComI.add(element);
		  if (nomeDoJogo.charAt(0)=='J') jogosComJ.add(element);
		  if (nomeDoJogo.charAt(0)=='K') jogosComK.add(element);
		  if (nomeDoJogo.charAt(0)=='L') jogosComL.add(element);
		  if (nomeDoJogo.charAt(0)=='M') jogosComM.add(element);
		  if (nomeDoJogo.charAt(0)=='N') jogosComN.add(element);
		  if (nomeDoJogo.charAt(0)=='O') jogosComO.add(element);
		  if (nomeDoJogo.charAt(0)=='P') jogosComP.add(element);
		  if (nomeDoJogo.charAt(0)=='Q') jogosComQ.add(element);
		  if (nomeDoJogo.charAt(0)=='R') jogosComR.add(element);
		  if (nomeDoJogo.charAt(0)=='S') jogosComS.add(element);
		  if (nomeDoJogo.charAt(0)=='T') jogosComT.add(element);
		  if (nomeDoJogo.charAt(0)=='U') jogosComU.add(element);
		  if (nomeDoJogo.charAt(0)=='V') jogosComV.add(element);
		  if (nomeDoJogo.charAt(0)=='W') jogosComW.add(element);
		  if (nomeDoJogo.charAt(0)=='X') jogosComX.add(element);
		  if (nomeDoJogo.charAt(0)=='Y') jogosComY.add(element);
		  if (nomeDoJogo.charAt(0)=='Z') jogosComZ.add(element);

		  int tipoDoJogo = element.getTipo();
		  if (tipoDoJogo==1) tipoAcao.add(element);
		  if (tipoDoJogo==2) tipoAdventure.add(element);
		  if (tipoDoJogo==3) tipoArcade.add(element);
		  if (tipoDoJogo==4) tipoEsporte.add(element);
		  if (tipoDoJogo==5) tipoEstrategia.add(element);
		  if (tipoDoJogo==6) tipoFPS.add(element);
		  if (tipoDoJogo==7) tipoInfantil.add(element);
		  if (tipoDoJogo==8) tipoLuta.add(element);
		  if (tipoDoJogo==9) tipoMMORPG.add(element);
		  if (tipoDoJogo==10) tipoQuebraCabeca.add(element);
		  if (tipoDoJogo==11) tipoRPG.add(element);
		  if (tipoDoJogo==12) tipoSimulacao.add(element);

	    }

	    Collections.sort(jogosComA,jogoNomeComparator);
	    Collections.sort(jogosComB,jogoNomeComparator);
	    Collections.sort(jogosComC,jogoNomeComparator);
	    Collections.sort(jogosComD,jogoNomeComparator);
	    Collections.sort(jogosComE,jogoNomeComparator);
	    Collections.sort(jogosComF,jogoNomeComparator);
	    Collections.sort(jogosComG,jogoNomeComparator);
	    Collections.sort(jogosComH,jogoNomeComparator);
	    Collections.sort(jogosComI,jogoNomeComparator);
	    Collections.sort(jogosComJ,jogoNomeComparator);
	    Collections.sort(jogosComK,jogoNomeComparator);
	    Collections.sort(jogosComL,jogoNomeComparator);
	    Collections.sort(jogosComM,jogoNomeComparator);
	    Collections.sort(jogosComN,jogoNomeComparator);
	    Collections.sort(jogosComO,jogoNomeComparator);
	    Collections.sort(jogosComP,jogoNomeComparator);
	    Collections.sort(jogosComQ,jogoNomeComparator);
	    Collections.sort(jogosComR,jogoNomeComparator);
	    Collections.sort(jogosComS,jogoNomeComparator);
	    Collections.sort(jogosComT,jogoNomeComparator);
	    Collections.sort(jogosComU,jogoNomeComparator);
	    Collections.sort(jogosComV,jogoNomeComparator);
	    Collections.sort(jogosComW,jogoNomeComparator);
	    Collections.sort(jogosComX,jogoNomeComparator);
	    Collections.sort(jogosComY,jogoNomeComparator);
	    Collections.sort(jogosComZ,jogoNomeComparator);

	    output.setValue("jogosComA", jogosComA);
	    output.setValue("jogosComB", jogosComB);
	    output.setValue("jogosComC", jogosComC);
	    output.setValue("jogosComD", jogosComD);
	    output.setValue("jogosComE", jogosComE);
	    output.setValue("jogosComF", jogosComF);
	    output.setValue("jogosComG", jogosComG);
	    output.setValue("jogosComH", jogosComH);
	    output.setValue("jogosComI", jogosComI);
	    output.setValue("jogosComJ", jogosComJ);
	    output.setValue("jogosComK", jogosComK);
	    output.setValue("jogosComL", jogosComL);
	    output.setValue("jogosComM", jogosComM);
	    output.setValue("jogosComN", jogosComN);
	    output.setValue("jogosComO", jogosComO);
	    output.setValue("jogosComP", jogosComP);
	    output.setValue("jogosComQ", jogosComQ);
	    output.setValue("jogosComR", jogosComR);
	    output.setValue("jogosComS", jogosComS);
	    output.setValue("jogosComT", jogosComT);
	    output.setValue("jogosComU", jogosComU);
	    output.setValue("jogosComV", jogosComV);
	    output.setValue("jogosComW", jogosComW);
	    output.setValue("jogosComX", jogosComX);
	    output.setValue("jogosComY", jogosComY);
	    output.setValue("jogosComZ", jogosComZ);

	    Collections.sort(tipoAcao,jogoNomeComparator);
	    Collections.sort(tipoAdventure,jogoNomeComparator);
	    Collections.sort(tipoArcade,jogoNomeComparator);
	    Collections.sort(tipoEsporte,jogoNomeComparator);
	    Collections.sort(tipoEstrategia,jogoNomeComparator);
	    Collections.sort(tipoFPS,jogoNomeComparator);
	    Collections.sort(tipoInfantil,jogoNomeComparator);
	    Collections.sort(tipoLuta,jogoNomeComparator);
	    Collections.sort(tipoMMORPG,jogoNomeComparator);
	    Collections.sort(tipoQuebraCabeca,jogoNomeComparator);
	    Collections.sort(tipoRPG,jogoNomeComparator);
	    Collections.sort(tipoSimulacao,jogoNomeComparator);

	    output.setValue("tipoAcao", tipoAcao);
	    output.setValue("tipoAdventure", tipoAdventure);
	    output.setValue("tipoArcade", tipoArcade);
	    output.setValue("tipoEsporte", tipoEsporte);
	    output.setValue("tipoEstrategia", tipoEstrategia);
	    output.setValue("tipoFPS", tipoFPS);
	    output.setValue("tipoInfantil", tipoInfantil);
	    output.setValue("tipoLuta", tipoLuta);
	    output.setValue("tipoMMORPG", tipoMMORPG);
	    output.setValue("tipoQuebraCabeca", tipoQuebraCabeca);
	    output.setValue("tipoRPG", tipoRPG);
	    output.setValue("tipoSimulacao", tipoSimulacao);

	    return SUCCESS;
    }

    /**
     * action que busca um emulador
     * @return
     * @throws ActionException
     */
    @SuppressWarnings("unchecked")
	public String emuladores() throws ActionException {
    	InfoHelper.setExtraInfo(this);
    	EmuladorCache emuCache = CacheManager.getCacheDeEmuladores();
    	EmuladorNomeComparator emuladorNomeComparator = new EmuladorNomeComparator();
		output.setValue("emuladores", emuCache.getCache());
		ArrayList<EmuladorVO> emuladores =  new ArrayList<EmuladorVO>(emuCache.getCache());
	    ArrayList<EmuladorVO> emuladoresComA = new ArrayList<EmuladorVO>();
	    ArrayList<EmuladorVO> emuladoresComB = new ArrayList<EmuladorVO>();
	    ArrayList<EmuladorVO> emuladoresComC = new ArrayList<EmuladorVO>();
	    ArrayList<EmuladorVO> emuladoresComD = new ArrayList<EmuladorVO>();
	    ArrayList<EmuladorVO> emuladoresComE = new ArrayList<EmuladorVO>();
	    ArrayList<EmuladorVO> emuladoresComF = new ArrayList<EmuladorVO>();
	    ArrayList<EmuladorVO> emuladoresComG = new ArrayList<EmuladorVO>();
	    ArrayList<EmuladorVO> emuladoresComH = new ArrayList<EmuladorVO>();
	    ArrayList<EmuladorVO> emuladoresComI = new ArrayList<EmuladorVO>();
	    ArrayList<EmuladorVO> emuladoresComJ = new ArrayList<EmuladorVO>();
	    ArrayList<EmuladorVO> emuladoresComK = new ArrayList<EmuladorVO>();
	    ArrayList<EmuladorVO> emuladoresComL = new ArrayList<EmuladorVO>();
	    ArrayList<EmuladorVO> emuladoresComM = new ArrayList<EmuladorVO>();
	    ArrayList<EmuladorVO> emuladoresComN = new ArrayList<EmuladorVO>();
	    ArrayList<EmuladorVO> emuladoresComO = new ArrayList<EmuladorVO>();
	    ArrayList<EmuladorVO> emuladoresComP = new ArrayList<EmuladorVO>();
	    ArrayList<EmuladorVO> emuladoresComQ = new ArrayList<EmuladorVO>();
	    ArrayList<EmuladorVO> emuladoresComR = new ArrayList<EmuladorVO>();
	    ArrayList<EmuladorVO> emuladoresComS = new ArrayList<EmuladorVO>();
	    ArrayList<EmuladorVO> emuladoresComT = new ArrayList<EmuladorVO>();
	    ArrayList<EmuladorVO> emuladoresComU = new ArrayList<EmuladorVO>();
	    ArrayList<EmuladorVO> emuladoresComV = new ArrayList<EmuladorVO>();
	    ArrayList<EmuladorVO> emuladoresComW = new ArrayList<EmuladorVO>();
	    ArrayList<EmuladorVO> emuladoresComX = new ArrayList<EmuladorVO>();
	    ArrayList<EmuladorVO> emuladoresComY = new ArrayList<EmuladorVO>();
	    ArrayList<EmuladorVO> emuladoresComZ = new ArrayList<EmuladorVO>();

	    ArrayList<EmuladorVO> tipoAmiga = new ArrayList<EmuladorVO>();
	    ArrayList<EmuladorVO> tipoAtari = new ArrayList<EmuladorVO>();
	    ArrayList<EmuladorVO> tipoGameBoy = new ArrayList<EmuladorVO>();
	    ArrayList<EmuladorVO> tipoInfinity = new ArrayList<EmuladorVO>();
	    ArrayList<EmuladorVO> tipoMacintosh = new ArrayList<EmuladorVO>();
	    ArrayList<EmuladorVO> tipoMasterSystem = new ArrayList<EmuladorVO>();
	    ArrayList<EmuladorVO> tipoMegaDrive = new ArrayList<EmuladorVO>();
	    ArrayList<EmuladorVO> tipoMSX = new ArrayList<EmuladorVO>();
	    ArrayList<EmuladorVO> tipoN64 = new ArrayList<EmuladorVO>();
	    ArrayList<EmuladorVO> tipoNeoGeo = new ArrayList<EmuladorVO>();
	    ArrayList<EmuladorVO> tipoNES = new ArrayList<EmuladorVO>();
	    ArrayList<EmuladorVO> tipoPlaystation = new ArrayList<EmuladorVO>();
	    ArrayList<EmuladorVO> tipoSCI = new ArrayList<EmuladorVO>();
	    ArrayList<EmuladorVO> tipoSNES = new ArrayList<EmuladorVO>();
	    ArrayList<EmuladorVO> tipoTK90 = new ArrayList<EmuladorVO>();
	    ArrayList<EmuladorVO> tipoWindows = new ArrayList<EmuladorVO>();
	    ArrayList<EmuladorVO> tipoVarios = new ArrayList<EmuladorVO>();

	    for (EmuladorVO element : emuladores) {
		  String nomeDoEmulador = element.getNome().toUpperCase();
		  if (nomeDoEmulador.charAt(0)=='A') emuladoresComA.add(element);
		  if (nomeDoEmulador.charAt(0)=='B') emuladoresComB.add(element);
		  if (nomeDoEmulador.charAt(0)=='C') emuladoresComC.add(element);
		  if (nomeDoEmulador.charAt(0)=='D') emuladoresComD.add(element);
		  if (nomeDoEmulador.charAt(0)=='E') emuladoresComE.add(element);
		  if (nomeDoEmulador.charAt(0)=='F') emuladoresComF.add(element);
		  if (nomeDoEmulador.charAt(0)=='G') emuladoresComG.add(element);
		  if (nomeDoEmulador.charAt(0)=='H') emuladoresComH.add(element);
		  if (nomeDoEmulador.charAt(0)=='I') emuladoresComI.add(element);
		  if (nomeDoEmulador.charAt(0)=='J') emuladoresComJ.add(element);
		  if (nomeDoEmulador.charAt(0)=='K') emuladoresComK.add(element);
		  if (nomeDoEmulador.charAt(0)=='L') emuladoresComL.add(element);
		  if (nomeDoEmulador.charAt(0)=='M') emuladoresComM.add(element);
		  if (nomeDoEmulador.charAt(0)=='N') emuladoresComN.add(element);
		  if (nomeDoEmulador.charAt(0)=='O') emuladoresComO.add(element);
		  if (nomeDoEmulador.charAt(0)=='P') emuladoresComP.add(element);
		  if (nomeDoEmulador.charAt(0)=='Q') emuladoresComQ.add(element);
		  if (nomeDoEmulador.charAt(0)=='R') emuladoresComR.add(element);
		  if (nomeDoEmulador.charAt(0)=='S') emuladoresComS.add(element);
		  if (nomeDoEmulador.charAt(0)=='T') emuladoresComT.add(element);
		  if (nomeDoEmulador.charAt(0)=='U') emuladoresComU.add(element);
		  if (nomeDoEmulador.charAt(0)=='V') emuladoresComV.add(element);
		  if (nomeDoEmulador.charAt(0)=='W') emuladoresComW.add(element);
		  if (nomeDoEmulador.charAt(0)=='X') emuladoresComX.add(element);
		  if (nomeDoEmulador.charAt(0)=='Y') emuladoresComY.add(element);
		  if (nomeDoEmulador.charAt(0)=='Z') emuladoresComZ.add(element);

		  int tipoDeEmulador = element.getTipo();

		  if (tipoDeEmulador==1) tipoAmiga.add(element);
		  if (tipoDeEmulador==2) tipoAtari.add(element);
		  if (tipoDeEmulador==3) tipoGameBoy.add(element);
		  if (tipoDeEmulador==4) tipoInfinity.add(element);
		  if (tipoDeEmulador==5) tipoMacintosh.add(element);
		  if (tipoDeEmulador==6) tipoMasterSystem.add(element);
		  if (tipoDeEmulador==7) tipoMegaDrive.add(element);
		  if (tipoDeEmulador==8) tipoMSX.add(element);
		  if (tipoDeEmulador==9) tipoN64.add(element);
		  if (tipoDeEmulador==10) tipoNeoGeo.add(element);
		  if (tipoDeEmulador==11) tipoNES.add(element);
		  if (tipoDeEmulador==12) tipoPlaystation.add(element);
		  if (tipoDeEmulador==13) tipoSCI.add(element);
		  if (tipoDeEmulador==14) tipoSNES.add(element);
		  if (tipoDeEmulador==15) tipoTK90.add(element);
		  if (tipoDeEmulador==16) tipoWindows.add(element);
		  if (tipoDeEmulador==17) tipoVarios.add(element);
	    }

	    Collections.sort(emuladoresComA,emuladorNomeComparator);
	    Collections.sort(emuladoresComB,emuladorNomeComparator);
	    Collections.sort(emuladoresComC,emuladorNomeComparator);
	    Collections.sort(emuladoresComD,emuladorNomeComparator);
	    Collections.sort(emuladoresComE,emuladorNomeComparator);
	    Collections.sort(emuladoresComF,emuladorNomeComparator);
	    Collections.sort(emuladoresComG,emuladorNomeComparator);
	    Collections.sort(emuladoresComH,emuladorNomeComparator);
	    Collections.sort(emuladoresComI,emuladorNomeComparator);
	    Collections.sort(emuladoresComJ,emuladorNomeComparator);
	    Collections.sort(emuladoresComK,emuladorNomeComparator);
	    Collections.sort(emuladoresComL,emuladorNomeComparator);
	    Collections.sort(emuladoresComM,emuladorNomeComparator);
	    Collections.sort(emuladoresComN,emuladorNomeComparator);
	    Collections.sort(emuladoresComO,emuladorNomeComparator);
	    Collections.sort(emuladoresComP,emuladorNomeComparator);
	    Collections.sort(emuladoresComQ,emuladorNomeComparator);
	    Collections.sort(emuladoresComR,emuladorNomeComparator);
	    Collections.sort(emuladoresComS,emuladorNomeComparator);
	    Collections.sort(emuladoresComT,emuladorNomeComparator);
	    Collections.sort(emuladoresComU,emuladorNomeComparator);
	    Collections.sort(emuladoresComV,emuladorNomeComparator);
	    Collections.sort(emuladoresComW,emuladorNomeComparator);
	    Collections.sort(emuladoresComX,emuladorNomeComparator);
	    Collections.sort(emuladoresComY,emuladorNomeComparator);
	    Collections.sort(emuladoresComZ,emuladorNomeComparator);

	    output.setValue("emuladoresComA", emuladoresComA);
	    output.setValue("emuladoresComB", emuladoresComB);
	    output.setValue("emuladoresComC", emuladoresComC);
	    output.setValue("emuladoresComD", emuladoresComD);
	    output.setValue("emuladoresComE", emuladoresComE);
	    output.setValue("emuladoresComF", emuladoresComF);
	    output.setValue("emuladoresComG", emuladoresComG);
	    output.setValue("emuladoresComH", emuladoresComH);
	    output.setValue("emuladoresComI", emuladoresComI);
	    output.setValue("emuladoresComJ", emuladoresComJ);
	    output.setValue("emuladoresComK", emuladoresComK);
	    output.setValue("emuladoresComL", emuladoresComL);
	    output.setValue("emuladoresComM", emuladoresComM);
	    output.setValue("emuladoresComN", emuladoresComN);
	    output.setValue("emuladoresComO", emuladoresComO);
	    output.setValue("emuladoresComP", emuladoresComP);
	    output.setValue("emuladoresComQ", emuladoresComQ);
	    output.setValue("emuladoresComR", emuladoresComR);
	    output.setValue("emuladoresComS", emuladoresComS);
	    output.setValue("emuladoresComT", emuladoresComT);
	    output.setValue("emuladoresComU", emuladoresComU);
	    output.setValue("emuladoresComV", emuladoresComV);
	    output.setValue("emuladoresComW", emuladoresComW);
	    output.setValue("emuladoresComX", emuladoresComX);
	    output.setValue("emuladoresComY", emuladoresComY);
	    output.setValue("emuladoresComZ", emuladoresComZ);

	    Collections.sort(tipoAmiga,emuladorNomeComparator);
	    Collections.sort(tipoAtari,emuladorNomeComparator);
	    Collections.sort(tipoGameBoy,emuladorNomeComparator);
	    Collections.sort(tipoInfinity,emuladorNomeComparator);
	    Collections.sort(tipoMacintosh,emuladorNomeComparator);
	    Collections.sort(tipoMasterSystem,emuladorNomeComparator);
	    Collections.sort(tipoMegaDrive,emuladorNomeComparator);
	    Collections.sort(tipoMSX,emuladorNomeComparator);
	    Collections.sort(tipoN64,emuladorNomeComparator);
	    Collections.sort(tipoNeoGeo,emuladorNomeComparator);
	    Collections.sort(tipoNES,emuladorNomeComparator);
	    Collections.sort(tipoPlaystation,emuladorNomeComparator);
	    Collections.sort(tipoSCI,emuladorNomeComparator);
	    Collections.sort(tipoSNES,emuladorNomeComparator);
	    Collections.sort(tipoTK90,emuladorNomeComparator);
	    Collections.sort(tipoWindows,emuladorNomeComparator);
	    Collections.sort(tipoVarios,emuladorNomeComparator);

	    output.setValue("tipoAmiga", tipoAmiga);
	    output.setValue("tipoAtari", tipoAtari);
	    output.setValue("tipoGameBoy", tipoGameBoy);
	    output.setValue("tipoInfinity", tipoInfinity);
	    output.setValue("tipoMacintosh", tipoMacintosh);
	    output.setValue("tipoMasterSystem", tipoMasterSystem);
	    output.setValue("tipoMegaDrive", tipoMegaDrive);
	    output.setValue("tipoMSX", tipoMSX);
	    output.setValue("tipoN64", tipoN64);
	    output.setValue("tipoNeoGeo", tipoNeoGeo);
	    output.setValue("tipoNES", tipoNES);
	    output.setValue("tipoPlaystation", tipoPlaystation);
	    output.setValue("tipoSCI", tipoSCI);
	    output.setValue("tipoSNES", tipoSNES);
	    output.setValue("tipoTK90", tipoTK90);
	    output.setValue("tipoWindows", tipoWindows);
	    output.setValue("tipoVarios", tipoVarios);

        return SUCCESS;
    }

}