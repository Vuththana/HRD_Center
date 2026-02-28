import React, { useReducer, useState } from 'react'
import { isNegative } from '../utils/isNegative';

function bankReducer(state, action) {
    switch(action.type) {
        case 'deposit' : {
            if(isNegative(action.amount)) {
                return state;
            } else {
                return state + action.amount;
            }
        }
        case 'withdraw' : {
            if(isNegative(action.amount)) {
                return state;
            } else {
                return state - action.amount;
            }
        }
        
        default : return state;
    }
}

export default function useBank(initialBalance = 0) {
    const [balance, dispatch] = useReducer(bankReducer, initialBalance);

    return {balance, dispatch};
}