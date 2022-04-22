/*
 * Copyright 2013 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.bitcoinj.params;

import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.core.Utils;

import static com.google.common.base.Preconditions.checkState;

/**
 * Parameters for the main production network on which people trade goods and services.
 */
public class MainNetParams extends NetworkParameters {
    public MainNetParams() {
        super();
        interval = INTERVAL;
        targetTimespan = TARGET_TIMESPAN;
        maxTarget = Utils.decodeCompactBits(0x1d00ffffL);
        dumpedPrivateKeyHeader = 128;
        addressHeader = 0;
        p2shHeader = 5;
        acceptableAddressCodes = new int[] { addressHeader, p2shHeader };
        port = 11080;
        packetMagic = 0xf9beb4d9L;
        genesisBlock.setDifficultyTarget(486604799L);
	genesisBlock.setTime(1411421930L);
	genesisBlock.setNonce(2552177790L);
        id = ID_MAINNET;
        subsidyDecreaseBlockCount = 210000;
        spendableCoinbaseDepth = 100;
        String genesisHash = genesisBlock.getHashAsString();
        checkState(genesisHash.equals("0000000040a867146dcc50bee85f69ac20addc33080c2a769c01200920251955"),
                genesisHash);

        // This contains (at a minimum) the blocks which are not BIP30 compliant. BIP30 changed how duplicate
        // transactions are handled. Duplicated transactions could occur in the case where a coinbase had the same
        // extraNonce and the same outputs but appeared at different heights, and greatly complicated re-org handling.
        // Having these here simplifies block connection logic considerably.
	checkpoints.put(0, new Sha256Hash("0000000040a867146dcc50bee85f69ac20addc33080c2a769c01200920251955"));
        checkpoints.put(10000, new Sha256Hash("0000000000000d79d3193be9094c988c81aab0a7c7d609a86a2dc61a4719376d"));
        checkpoints.put(20000, new Sha256Hash("00000000000008d637ebccc49097bd4b3e0feb6491c8c12ec0a65d87caee0eb4"));
        checkpoints.put(371900, new Sha256Hash("00000000000d4eff28cd43af373914a511f58fbe9a2b1c4efd3b537493330cf3"));

        dnsSeeds = new String[] {
	         "aurumcoin.cointech.net",        // 
                 "mobi.cointech.net",         // 
                 "pool.cointech.net",  // 
                 "seed.cointech.net",       // 
		};
    }

    private static MainNetParams instance;
    public static synchronized MainNetParams get() {
        if (instance == null) {
            instance = new MainNetParams();
        }
        return instance;
    }

    @Override
    public String getPaymentProtocolId() {
        return PAYMENT_PROTOCOL_ID_MAINNET;
    }
}
